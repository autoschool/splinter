package ru.yandex.autoschool.splinter.config;

import freemarker.template.Configuration;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class ApplicationConfig {
    public final static int POSTS_PER_PAGE = 3;
    private static Configuration configuration = new Configuration();
    public static Configuration getFreemarkerConfiguration() {
        return configuration;
    }
}
