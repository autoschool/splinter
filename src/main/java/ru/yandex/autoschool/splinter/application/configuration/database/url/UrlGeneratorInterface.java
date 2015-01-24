package ru.yandex.autoschool.splinter.application.configuration.database.url;

import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public interface UrlGeneratorInterface {
    public String generateUrl(DatabaseConfiguration configuration) throws IllegalArgumentException;
}
