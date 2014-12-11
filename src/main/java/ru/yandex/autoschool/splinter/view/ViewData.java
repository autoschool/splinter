package ru.yandex.autoschool.splinter.view;

import java.util.HashMap;

/**
 * Created by pacahon on 11.12.14.
 */
public class ViewData {
    public HashMap<String, Object> data;

    public ViewData() {
        this.data = new HashMap<>();
        this.set("model", "");
    }

    public void set(String key, Object value) {
        this.data.put(key, value);
    }

    public HashMap<String, Object> getData() {
        return this.data;
    }
}
