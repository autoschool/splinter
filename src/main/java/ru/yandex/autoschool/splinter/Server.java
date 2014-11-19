package ru.yandex.autoschool.splinter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import ru.yandex.autoschool.splinter.service.DatabaseProvider;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * @author eroshenkoam
 * @version %I%, %G%
 * @since 1.0
 */
public class Server extends ResourceConfig {

    public Server() {
        register(FreemarkerMvcFeature.class);

        register(new DynamicFeature() {
            @Override
            public void configure(ResourceInfo resourceInfo, FeatureContext context) {
                context.register(DatabaseProvider.class);
            }
        });

        packages(Server.class.getPackage().getName());
    }
}
