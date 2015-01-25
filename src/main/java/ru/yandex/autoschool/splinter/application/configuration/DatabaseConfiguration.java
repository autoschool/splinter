package ru.yandex.autoschool.splinter.application.configuration;

import ru.yandex.autoschool.splinter.application.Environment;
import ru.yandex.autoschool.splinter.application.configuration.database.Driver;
import ru.yandex.autoschool.splinter.application.configuration.database.Location;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Use;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
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
    
    public DatabaseConfiguration(Environment environment) {
        PropertyLoader.populate(this, LoadProperties(environment));
    }
    
    private Properties LoadProperties(Environment environment) {
        String fileName = String.format("%s.properties", Environment.getShortName(environment));
        String localFileName = String.format("%s.local.properties", Environment.getShortName(environment));
        Properties properties = new Properties();
        ClassLoader classLoader = DatabaseConfiguration.class.getClassLoader();
        InputStream resource = classLoader.getResourceAsStream("configuration/" + fileName);
        if (resource != null) {
            try {
                properties.load(resource);
            } catch (IOException e) {
                // do nothing, leave empty properties
            }
        }
        resource = classLoader.getResourceAsStream("configuration/" + localFileName);
        if (resource != null) {
            try {
                properties.load(resource);
            } catch (IOException e) {
                // do nothing, leave empty properties
            }
        }
        return properties;
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
