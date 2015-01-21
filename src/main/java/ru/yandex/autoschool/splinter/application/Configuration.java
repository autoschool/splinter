package ru.yandex.autoschool.splinter.application;

import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
import ru.yandex.autoschool.splinter.application.configuration.database.url.UrlGenerator;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Configuration {
    public static int POSTS_PER_PAGE = 3;
    private String databaseUrl;
    final private DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
    public Configuration() {
        databaseUrl = new UrlGenerator().generateUrl(databaseConfiguration);
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
}
