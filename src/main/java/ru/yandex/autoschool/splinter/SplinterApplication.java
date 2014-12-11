package ru.yandex.autoschool.splinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.autoschool.splinter.config.ApplicationConfig;

/**
 * This is the main application class which may get refactored soon. The only purpose of it at the moment is to store
 * various links to different components - logger, database service, etc.
 *
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
final public class SplinterApplication {
    final public static Logger LOGGER = LoggerFactory.getLogger(SplinterApplication.class);
    final public static ApplicationConfig CONFIG = new ApplicationConfig();
    /**
     * Instantiate this.
     */
    private SplinterApplication() {}
}
