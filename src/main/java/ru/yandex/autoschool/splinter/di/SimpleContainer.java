package ru.yandex.autoschool.splinter.di;

import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.application.Splinter;
import ru.yandex.autoschool.splinter.view.freemarker.SharedVariablesManager;
import ru.yandex.autoschool.splinter.view.freemarker.TemplateObjectFactory;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class SimpleContainer {
    private static Logger logger;
    private static Splinter application;
    private static SharedVariablesManager sharedVariablesManager;
    private static TemplateObjectFactory freemarkerConfiguration;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SimpleContainer.logger = logger;
    }

    public static Splinter getApplication() {
        return application;
    }

    public static void setApplication(Splinter application) {
        SimpleContainer.application = application;
    }

    public static SharedVariablesManager getSharedVariablesManager() {
        return sharedVariablesManager;
    }

    public static void setSharedVariablesManager(SharedVariablesManager sharedVariablesManager) {
        SimpleContainer.sharedVariablesManager = sharedVariablesManager;
    }

    public static TemplateObjectFactory getFreemarkerConfiguration() {
        return freemarkerConfiguration;
    }

    public static void setFreemarkerConfiguration(TemplateObjectFactory freemarkerConfiguration) {
        SimpleContainer.freemarkerConfiguration = freemarkerConfiguration;
    }
}
