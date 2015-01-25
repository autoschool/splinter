package ru.yandex.autoschool.splinter.application;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;
import ru.yandex.qatools.properties.annotations.Use;
import ru.yandex.qatools.properties.annotations.With;
import ru.yandex.qatools.properties.providers.MapOrSyspropPathReplacerProvider;
import ru.yandex.autoschool.splinter.application.configuration.properties.EnvironmentConverter;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
@With(MapOrSyspropPathReplacerProvider.class)
@Resource.Classpath("splinter.properties")
public class Splinter {
    @Property("splinter.name")
    private String name;
    @Property("splinter.version")
    private String version;
    @Use(EnvironmentConverter.class)
    @Property("splinter.environment")
    private Environment environment;
    private Configuration configuration = new Configuration();
    public Splinter() {
        PropertyLoader.populate(this);
        if (environment == null) {
            String message = "No environment set, please check your `-Denvironment` setting and `environment` " + 
                    "property - the resulting one MUST be either dev, prod, or test" +
                    System.getProperty("line.separator") + 
                    "see README.md for usage hints.";
            throw new IllegalStateException(message);
        }
    }
    public String getName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
    public Environment getEnvironment() {
        return environment;
    }
    public Configuration getConfiguration() {
        return configuration;
    }
}
