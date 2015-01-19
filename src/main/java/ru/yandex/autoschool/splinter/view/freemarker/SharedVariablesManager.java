package ru.yandex.autoschool.splinter.view.freemarker;

import ru.yandex.autoschool.splinter.application.Splinter;

import javax.inject.Inject;
import java.util.HashMap;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class SharedVariablesManager {
    @Inject
    private Splinter application;
    public HashMap<String, Object> provide() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("splinter", application);
        return map;
    }
}
