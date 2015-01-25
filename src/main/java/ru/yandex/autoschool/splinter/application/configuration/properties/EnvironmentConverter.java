package ru.yandex.autoschool.splinter.application.configuration.properties;

import org.apache.commons.beanutils.Converter;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class EnvironmentConverter implements Converter {
    @Override
    public Object convert(Class clazz, Object property) {
        if (!(property instanceof String)) {
            throw new IllegalArgumentException("Only strings may be converted");
        }
        return ru.yandex.autoschool.splinter.application.EnvironmentConverter.convertFromString((String) property);
    }
}
