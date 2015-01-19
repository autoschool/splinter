package ru.yandex.autoschool.splinter.di.provider;

import org.glassfish.hk2.api.Factory;
import ru.yandex.autoschool.splinter.view.freemarker.TemplateObjectFactory;

/**
 * //todo this implementation uses hardcoded class, while Freemarker MVCFeature class introduces special variables for
 * this. It's better to rely on them.
 * 
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class FreemarkerConfigurationProvider implements Factory<TemplateObjectFactory> {
    public TemplateObjectFactory provide() {
        TemplateObjectFactory factory = null;
        try {
            factory = new TemplateObjectFactory();
        } catch (Exception e) {
            // @todo dying silently. This is bad, but i don't know how to do it properly.
        }
        return factory;
    }
    public void dispose(TemplateObjectFactory factory) {
        // the glorious donothing method.
    }
}
