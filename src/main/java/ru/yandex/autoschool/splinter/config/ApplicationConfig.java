package ru.yandex.autoschool.splinter.config;

import freemarker.template.Configuration;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
final public class ApplicationConfig {
    public static final int POSTS_PER_PAGE = 3;
    private Configuration freemarkerConfiguration;
    public void setFreemarkerConfiguration(Configuration configuration)
    {
        freemarkerConfiguration = configuration;
    }
    @SuppressWarnings("unused")
    public Configuration getFreemarkerConfiguration()
    {
        return freemarkerConfiguration;
    }
}
