package ru.yandex.autoschool.splinter;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.application.Splinter;
import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
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
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                // Template method interceptor
                bind(UserDataInterceptor.class).to(WriterInterceptor.class).in(Singleton.class);
            }
        });

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
        
    }
    
    private void registerBinders(final Splinter application) {
        final Logger logger = new LoggerProvider().provide();
        logger.debug("Dependency injection start");
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
                bind(new SharedVariablesManager()).to(SharedVariablesManager.class);
            }
        });
//        final TemplateObjectFactory factory = new FreemarkerConfigurationProvider().provide();
//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(factory).to(Configuration.class);
//            }
//        });
//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(factory).to(TemplateObjectFactory.class);
//            }
//        });
        logger.debug("Finished dependency injection registration");
    }
}
