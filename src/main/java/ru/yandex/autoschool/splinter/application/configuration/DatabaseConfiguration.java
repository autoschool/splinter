package ru.yandex.autoschool.splinter.application.configuration;

import ru.yandex.autoschool.splinter.application.configuration.database.Driver;
import ru.yandex.autoschool.splinter.application.configuration.database.Location;
import ru.yandex.autoschool.splinter.application.configuration.properties.MultiplePropertySourceProvider;
import ru.yandex.autoschool.splinter.application.configuration.properties.MultiplePropertySources;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Use;
import ru.yandex.qatools.properties.annotations.With;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@With(MultiplePropertySourceProvider.class)
@MultiplePropertySources(
        sources = {
                "configuration/${system.environment}.properties",
                "configuration/${system.environment}.local.properties"
        }
)
@SuppressWarnings("unused")
public class DatabaseConfiguration {
    @Use(ru.yandex.qatools.properties.converters.EnumConverter.class)
    @Property("database.location")
    private Location location;
    @Use(ru.yandex.qatools.properties.converters.EnumConverter.class)
    @Property("database.driver")
    private Driver driver;
    @Property("database.name")
    private String name = "splinter";
    @Property("database.user")
    private String user;
    @Property("database.password")
    private String password;
    @Property("database.host")
    private String host;
    @Property("database.port")
    private Integer port;
    @Property("database.path")
    private String path;
    
    public DatabaseConfiguration() {
        PropertyLoader.populate(this);
    }

    public Location getLocation() {
        return location;
    }

    public Driver getDriver() {
        return driver;
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

    public String getHost() {
        return host;
    }
    
    public Integer getPort() {
        return port;
    }
    
    public String getPath() {
        return path;
    }
}
