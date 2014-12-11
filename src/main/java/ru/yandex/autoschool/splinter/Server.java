package ru.yandex.autoschool.splinter;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import ru.yandex.autoschool.splinter.service.AuthProvider;
import ru.yandex.autoschool.splinter.service.DatabaseProvider;

import javax.inject.Singleton;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.WriterInterceptor;
import ru.yandex.autoschool.splinter.interceptions.UserDataInterceptor;
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
}
