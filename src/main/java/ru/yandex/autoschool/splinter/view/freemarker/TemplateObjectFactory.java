package ru.yandex.autoschool.splinter.view.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.di.SimpleContainer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class TemplateObjectFactory extends Configuration {
    public TemplateObjectFactory() throws TemplateModelException, IOException, NullPointerException {
        SimpleContainer.setFreemarkerConfiguration(this);
        Logger logger = SimpleContainer.getLogger();
        SharedVariablesManager sharedVariablesManager = SimpleContainer.getSharedVariablesManager();
        logger.info("Creating injected freemarker configuration instance");
        
        String resourceRootDirectoryUri = TemplateObjectFactory.class.getClassLoader().getResource("/").getFile();
        logger.debug(
                "Instantiating new file template loader with following resource directory URI: {}",
                resourceRootDirectoryUri
        );
        File resourceRootDirectory = new File(resourceRootDirectoryUri);
        setDirectoryForTemplateLoading(resourceRootDirectory);
        
        for (Map.Entry<String, Object> entry : sharedVariablesManager.provide().entrySet()) {
            setSharedVariable(entry.getKey(), entry.getValue());
        }
    }
}
