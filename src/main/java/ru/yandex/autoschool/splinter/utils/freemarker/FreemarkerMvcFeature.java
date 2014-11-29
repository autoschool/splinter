/**
 * Yes, this is shameless ripoff for weird purposes
 */
package ru.yandex.autoschool.splinter.utils.freemarker;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * {@link Feature} used to add support for {@link MvcFeature MVC} and Freemarker templates.
 * <p/>
 * Note: This feature also registers {@link MvcFeature}.
 *
 * @author Michal Gajdos (michal.gajdos at oracle.com)
 */
@SuppressWarnings("unused")
@ConstrainedTo(RuntimeType.SERVER)
public final class FreemarkerMvcFeature implements Feature {
    private static final String SUFFIX = ".freemarker";
    public static final String TEMPLATES_BASE_PATH = "jersey.config.server.mvc.templateBasePath.freemarker";
    public static final String CACHE_TEMPLATES = "jersey.config.server.mvc.caching.freemarker";
    public static final String TEMPLATE_OBJECT_FACTORY = "jersey.config.server.mvc.factory.freemarker";
    public static final String ENCODING = "jersey.config.server.mvc.encoding.freemarker";

    public FreemarkerMvcFeature() {
    }

    public boolean configure(FeatureContext context) {
        Configuration config = context.getConfiguration();
        if(!config.isRegistered(FreemarkerViewProcessor.class)) {
            context.register(FreemarkerViewProcessor.class);
            if(!config.isRegistered(MvcFeature.class)) {
                context.register(MvcFeature.class);
            }

            return true;
        } else {
            return false;
        }
    }
}
