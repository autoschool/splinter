package ru.yandex.autoschool.splinter.config.database;

import ru.yandex.qatools.properties.annotations.Resource;
import ru.yandex.qatools.properties.annotations.With;
import ru.yandex.qatools.properties.providers.MapOrSyspropPathReplacerProvider;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@With(MapOrSyspropPathReplacerProvider.class)
@Resource.Classpath("database.default.properties")
public class DefaultDatabaseConfig extends AbstractDatabaseConfig {
    public DefaultDatabaseConfig() {
        populate();
    }
}
