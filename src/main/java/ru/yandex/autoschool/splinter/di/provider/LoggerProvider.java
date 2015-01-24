package ru.yandex.autoschool.splinter.di.provider;

import org.apache.log4j.BasicConfigurator;
import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.autoschool.splinter.application.Splinter;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class LoggerProvider implements Factory<Logger> {
    public Logger provide() {
        if (!org.apache.log4j.Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
            BasicConfigurator.configure();
        }
        return LoggerFactory.getLogger(Splinter.class);
    }
    public void dispose(Logger logger) {
        // do nothing
    }
}
