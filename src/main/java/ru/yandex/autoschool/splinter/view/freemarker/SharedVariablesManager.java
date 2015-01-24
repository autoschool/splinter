package ru.yandex.autoschool.splinter.view.freemarker;

import java.util.HashMap;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class SharedVariablesManager {
    private HashMap<String, Object> sharedVariables = new HashMap<>();
    public HashMap<String, Object> provide() {
        return sharedVariables;
    }
    public void put(String key, Object variable) {
        sharedVariables.put(key, variable);
    }
}
