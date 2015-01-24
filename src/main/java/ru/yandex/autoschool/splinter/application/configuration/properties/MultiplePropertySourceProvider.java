package ru.yandex.autoschool.splinter.application.configuration.properties;

import ru.yandex.qatools.properties.providers.MapOrSyspropPathReplacerProvider;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.yandex.qatools.properties.utils.PropertiesUtils.readProperties;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class MultiplePropertySourceProvider extends MapOrSyspropPathReplacerProvider {

    @Override
    public <T> Properties provide(T bean, Properties properties) {
        Class<?> clazz = bean.getClass();

        if (have(clazz, MultiplePropertySources.class)) {
            for (String source : getSources(clazz, properties)) {
                if (sourceExists(source)) {
                    properties.putAll(readProperties(getClassLoaderAlternate().getResourceAsStream(source)));
                }
            }
        }

        properties.putAll(System.getProperties());
        return properties;
    }

    /**
     * Original implementation was declared as private, so i had to override it.
     */
    protected ClassLoader getClassLoaderAlternate() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (SecurityException e) {
            // do nothing, return system class loader later.
        }
        if (classLoader == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return classLoader;
    }
    protected String[] getSources(Class<?> clazz, Properties properties) {
        String[] sources = clazz.getAnnotation(MultiplePropertySources.class).sources();
        for (int i = 0; i < sources.length; i++) {
            String mapReplaces = replaceWithMapPropsAlternate(sources[i], properties);
            sources[i] = replaceWithSystemPropsAlternate(mapReplaces);
        }
        return sources;
    }

    /**
     * Original implementation was declared as private, so i had to override it.
     */
    protected String replaceWithMapPropsAlternate(String path, Properties properties) {
        Matcher matcher = Pattern.compile(MAP_PROP_KEY_PATTERN).matcher(path);
        String replaced = path;
        while (matcher.find()) {
            replaced = replaced.replace(matcher.group(0), properties.getProperty(matcher.group(1), ""));
        }
        return replaced;
    }

    /**
     * Original implementation was declared as private, so i had to override it.
     */
    protected String replaceWithSystemPropsAlternate(String path) {
        Matcher matcher = Pattern.compile(SYS_PROP_KEY_PATTERN).matcher(path);
        String replaced = path;
        while (matcher.find()) {
            replaced = replaced.replace(matcher.group(0), System.getProperty(matcher.group(1), ""));
        }
        return replaced;
    }
    
    protected boolean sourceExists(String source) {
        return getClassLoaderAlternate().getResource(source) != null;
    }
}
