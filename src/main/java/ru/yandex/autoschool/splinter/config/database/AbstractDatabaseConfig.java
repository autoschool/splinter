package ru.yandex.autoschool.splinter.config.database;

import ru.yandex.autoschool.splinter.config.database.url.H2UrlGenerator;
import ru.yandex.autoschool.splinter.config.database.url.MysqlUrlGenerator;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Use;
import ru.yandex.qatools.properties.converters.EnumConverter;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class AbstractDatabaseConfig {
    public static enum LOCATION { MEMORY, FILE, NETWORK }
    public static enum DRIVER { H2, MYSQL }
    @Use(EnumConverter.class)
    @Property("db.location")
    protected LOCATION location = LOCATION.MEMORY;
    @Use(EnumConverter.class)
    @Property("db.driver")
    protected DRIVER driver = DRIVER.H2;
    @Property("db.path")
    protected String path;
    @Property("db.host")
    protected String host = "localhost";
    @Property("db.port")
    protected int port;
    @Property("db.name")
    protected String name = "splinter";
    @Property("db.user")
    private String user;
    @Property("db.password")
    private String password;

    protected void populate() {
        PropertyLoader.populate(this);
        if (location == null) {
            String message = "Database properties file has to specify database location, either `memory`, `file` or " +
                    "`network`, ex: `location:network`";
            throw new IllegalStateException(message);
        }
        if (name == null) {
            String message = "Database properties file has to specify database name";
            throw new IllegalStateException(message);
        }
    }

    public LOCATION getLocation() {
        return location;
    }

    public DRIVER getDriver() {
        return driver;
    }

    public String getPath() {
        if (path == null) {
            return getName();
        }
        return path + "/" + getName();
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

    public String getUrl() {
        if (getDriver() == DRIVER.MYSQL) {
            if (location != LOCATION.NETWORK) {
                String message = "Mysql database is currently accessible only via network. Please change appropriate " +
                        "value in your properties file.";
                throw new IllegalArgumentException(message);
            }
            return MysqlUrlGenerator.getNetworkDbUrl(getHost(), getPort(), getName(), getUser(), getPassword());
        } else if (getDriver() == DRIVER.H2) {
            switch (getLocation()) {
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
}
