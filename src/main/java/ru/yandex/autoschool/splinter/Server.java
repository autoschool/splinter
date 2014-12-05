package ru.yandex.autoschool.splinter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import ru.yandex.autoschool.splinter.service.AuthProvider;
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
}
