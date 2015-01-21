package ru.yandex.autoschool.splinter;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.application.Splinter;
import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
import ru.yandex.autoschool.splinter.application.configuration.database.Driver;
import ru.yandex.autoschool.splinter.di.SimpleContainer;
import ru.yandex.autoschool.splinter.di.provider.LoggerProvider;
import ru.yandex.autoschool.splinter.service.AuthProvider;
import ru.yandex.autoschool.splinter.service.DatabaseProvider;

import javax.inject.Singleton;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.WriterInterceptor;
import ru.yandex.autoschool.splinter.interceptions.UserDataInterceptor;
import ru.yandex.autoschool.splinter.view.freemarker.SharedVariablesManager;

/**
 * @author eroshenkoam
 * @version %I%, %G%
 * @since 1.0
 */
public class Server extends ResourceConfig {
    
    public Server() {
        Splinter splinter = new Splinter();
        registerBinders(splinter);
        
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        register(FreemarkerMvcFeature.class);

        register(new DynamicFeature() {
            @Override
            public void configure(ResourceInfo resourceInfo, FeatureContext context) {
                context.register(DatabaseProvider.class);
                context.register(AuthProvider.class);
            }
        });

        packages(Server.class.getPackage().getName());
        
        registerSharedVariables();
    }
    
    private void registerBinders(final Splinter application) {
        final Logger logger = new LoggerProvider().provide();
        logger.debug("Dependency injection start");
        
        SimpleContainer.setApplication(application);
        SimpleContainer.setLogger(logger);
        SimpleContainer.setSharedVariablesManager(new SharedVariablesManager());

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                // Template method interceptor
                bind(UserDataInterceptor.class).to(WriterInterceptor.class).in(Singleton.class);
            }
        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new DatabaseConfiguration()).to(DatabaseConfiguration.class);
            }
        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(application).to(Splinter.class);
            }
        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(logger).to(Logger.class);
            }
        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(SimpleContainer.getSharedVariablesManager()).to(SharedVariablesManager.class);
            }
        });
        logger.debug("Finished dependency injection registration");
    }
    private void registerSharedVariables() {
        SharedVariablesManager sharedVariablesManager = SimpleContainer.getSharedVariablesManager();
        Splinter application = SimpleContainer.getApplication();
        sharedVariablesManager.put("application_version", application.getVersion());
        sharedVariablesManager.put("application_environment", application.getEnvironment().toString().toLowerCase());
        sharedVariablesManager.put("application_name", application.getName());
        Driver databaseDriver = application.getConfiguration().getDatabaseConfiguration().getDriver();
        sharedVariablesManager.put("application_database_driver", databaseDriver.toString().toLowerCase());
    }
}
