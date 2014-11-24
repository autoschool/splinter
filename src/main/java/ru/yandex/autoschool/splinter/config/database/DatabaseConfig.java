package ru.yandex.autoschool.splinter.config.database;

import ru.yandex.autoschool.splinter.config.database.url.H2UrlGenerator;
import ru.yandex.autoschool.splinter.config.database.url.MysqlUrlGenerator;
import ru.yandex.autoschool.splinter.config.database.AbstractDatabaseConfig.DRIVER;
import ru.yandex.autoschool.splinter.config.database.AbstractDatabaseConfig.LOCATION;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class DatabaseConfig {
    private LOCATION location;
    private DRIVER driver;
    private String path;
    private String host;
    private int port;
    private String name;
    private String user;
    private String password;
    public DatabaseConfig() {
        DefaultDatabaseConfig defaultDbConfig = new DefaultDatabaseConfig();
        LocalDatabaseConfig localDbConfig = new LocalDatabaseConfig();
        // oh wtf is this shit
        // man, do you know how to code?
        location = localDbConfig.getLocation() != null ? localDbConfig.getLocation() : defaultDbConfig.getLocation();
        driver = localDbConfig.getDriver() != null ? localDbConfig.getDriver() : defaultDbConfig.getDriver();
        path = localDbConfig.getPath() != null ? localDbConfig.getPath() : defaultDbConfig.getPath();
        host = localDbConfig.getHost() != null ? localDbConfig.getHost() : defaultDbConfig.getHost();
        port = localDbConfig.getPort() > 0 ? localDbConfig.getPort() : defaultDbConfig.getPort();
        name = localDbConfig.getName() != null ? localDbConfig.getName() : defaultDbConfig.getName();
        user = localDbConfig.getUser() != null ? localDbConfig.getUser() : defaultDbConfig.getUser();
        password = localDbConfig.getPassword() != null ? localDbConfig.getPassword() : defaultDbConfig.getPassword();
    }
    public String getUrl() throws IllegalArgumentException {
        if (driver == DRIVER.MYSQL) {
            if (location != LOCATION.NETWORK) {
                String message = "Mysql database is currently accessible only via network. Please change appropriate " +
                        "value in your properties file (`db.location=network`).";
                throw new IllegalArgumentException(message);
            }
            return MysqlUrlGenerator.getNetworkDbUrl(getHost(), getPort(), getName(), getUser(), getPassword());
        } else if (driver == DRIVER.H2) {
            switch (location) {
                case NETWORK:
                    return H2UrlGenerator.getNetworkDbUrl(getHost(), getPort(), getPath(), getUser(), getPassword());
                case FILE:
                    return H2UrlGenerator.getFileDbUrl(getPath(), getUser(), getPassword());
                case MEMORY:
                    return H2UrlGenerator.getInMemoryDbUrl(getName(), getUser(), getPassword());
            }
        }
        return null;
    }

    public LOCATION getLocation() {
        return location;
    }

    public DRIVER getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
