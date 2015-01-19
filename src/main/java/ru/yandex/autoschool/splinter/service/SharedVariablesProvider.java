package ru.yandex.autoschool.splinter.service;

import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.application.Splinter;
import ru.yandex.autoschool.splinter.view.freemarker.TemplateObjectFactory;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class SharedVariablesProvider implements ContainerRequestFilter {
    @Inject
    TemplateObjectFactory configuration;
    @Inject
    Splinter application;
    @Inject
    Logger logger;
    public void filter(ContainerRequestContext context) {
        logger.debug("Injecting shared variables");
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("application:version", application.getVersion());
        variables.put("application:name", application.getName());
        variables.put("application:environment", application.getEnvironment());
        variables.put("splinter", application);
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            try {
                logger.debug(String.format("Injecting shared variable %s", entry.getKey()));
                configuration.setSharedVariable(entry.getKey(), entry.getValue());
            } catch (TemplateModelException e) {
                logger.error(String.format("Failed to set shared variable %s", entry.getKey()));
            }
        }
    }
}
