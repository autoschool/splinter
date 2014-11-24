package ru.yandex.autoschool.splinter.config;

import ru.yandex.autoschool.splinter.config.database.DatabaseConfig;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class ServerConfig {
    private DatabaseConfig databaseConfig;
    public ServerConfig() {
        databaseConfig = new DatabaseConfig();
    }
    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }
}
