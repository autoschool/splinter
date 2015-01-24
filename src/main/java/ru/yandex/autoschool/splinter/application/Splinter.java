package ru.yandex.autoschool.splinter.application;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;
import ru.yandex.qatools.properties.annotations.With;
import ru.yandex.qatools.properties.providers.MapOrSyspropPathReplacerProvider;


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
    private Environment environment = EnvironmentConverter.convertFromString(System.getProperty("environment", "prod"));
    private Configuration configuration = new Configuration();
    public Splinter() {
        PropertyLoader.populate(this);
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
