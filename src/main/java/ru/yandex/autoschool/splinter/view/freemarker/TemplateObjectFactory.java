package ru.yandex.autoschool.splinter.view.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import ru.yandex.autoschool.splinter.SplinterApplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class TemplateObjectFactory extends Configuration {
    private static HashMap<String, Object> preloadedSharedVariables = new HashMap<>();
    public TemplateObjectFactory() throws TemplateModelException, IOException, NullPointerException {
        SplinterApplication.LOGGER.info("Creating injected freemarker configuration instance");
        
        String resourceRootDirectoryUri = TemplateObjectFactory.class.getClassLoader().getResource("/").getFile();
        SplinterApplication.LOGGER.debug(
                "Instantiating new file template loader with following resource directory URI: {}",
                resourceRootDirectoryUri
        );
        File resourceRootDirectory = new File(resourceRootDirectoryUri);
        setDirectoryForTemplateLoading(resourceRootDirectory);
        
        SplinterApplication.CONFIG.setFreemarkerConfiguration(this);
        for (Map.Entry<String, Object> entry : preloadedSharedVariables.entrySet()) {
            setSharedVariable(entry.getKey(), entry.getValue());
        }
        SplinterApplication.LOGGER.info(
                "If you need to set shared variable after TemplateObjectFactory creation, use " +
                        "SplinterApplication.CONFIG.getFreemarkerConfiguration() to get necessary instance"
        );
    }
    
    @SuppressWarnings("unused")
    public static void addSharedVariable(String name, Object variable) {
        SplinterApplication.LOGGER.debug(
                "Adding shared Freemarker variable \"{}\", it will be automatically loaded in all new instances", name
        );
        preloadedSharedVariables.put(name, variable);
    }
}
