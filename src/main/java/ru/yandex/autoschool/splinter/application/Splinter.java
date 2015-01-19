package ru.yandex.autoschool.splinter.application;

import org.glassfish.hk2.api.ServiceLocator;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;
import ru.yandex.qatools.properties.annotations.With;
import ru.yandex.qatools.properties.providers.MapOrSyspropPathReplacerProvider;

import javax.inject.Inject;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
@With(MapOrSyspropPathReplacerProvider.class)
@Resource.Classpath("splinter.properties")
public class Splinter {
    @Inject
    private ServiceLocator serviceLocator;
    @Property("splinter.name")
    private String name;
    @Property("splinter.version")
    private String version;
    private Environment environment = EnvironmentConverter.convertFromString(System.getProperty("environment", "prod"));
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
        
//    public static void registerBinders(ResourceConfig resourceConfig) {
//        LoggerProvider provider = new LoggerProvider();
//        final Logger logger = provider.provide();
//        logger.info("Splinter application initialization");
//        logger.debug(String.format("Version: %s, environment: %s", version, environment));
//        resourceConfig.register(
//                new AbstractBinder() {
//                    @Override
//                    protected void configure() {
//                        bind(Splinter.class).to(Splinter.class).in(Singleton.class);
//                    }
//                }
//        );
//        resourceConfig.register(
//                new AbstractBinder() {
//                    @Override
//                    protected void configure() {
//                        bindFactory(FreemarkerConfigurationProvider.class)
//                                .to(freemarker.template.Configuration.class)
//                                .in(Singleton.class);
//                    }
//                }
//        );
//        resourceConfig.register(
//                new AbstractBinder() {
//                    @Override
//                    protected void configure() {
//                        bind(logger).to(Logger.class);
//                    }
//                }
//        );
//        resourceConfig.register(
//                new AbstractBinder() {
//                    @Override
//                    protected void configure() {
//                        bind(SharedVariablesManager.class).to(SharedVariablesManager.class).in(Singleton.class);
//                    }
//                }
//        );
//        logger.debug("Registered all binders");
//    }
}
