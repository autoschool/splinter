package ru.yandex.autoschool.splinter.di.provider;

import freemarker.template.Configuration;
import org.glassfish.hk2.api.Factory;
import ru.yandex.autoschool.splinter.view.freemarker.SharedVariablesManager;

import javax.inject.Inject;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class SharedVariablesManagerProvider implements Factory<SharedVariablesManager> {
    @Inject
    private Configuration configuration;
    public SharedVariablesManager provide() {
        return new SharedVariablesManager();
    }
    public void dispose(SharedVariablesManager manager) {
        // void! This resource doesn't need special disposal
    }
}
