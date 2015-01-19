package ru.yandex.autoschool.splinter.application;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class EnvironmentConverter {
    public static Environment convertFromString(String environment)
    {
        if (environment == null) {
            throw new IllegalArgumentException("Null passed as environment name");
        }
        switch (environment) {
            case "prod":
                // falling forward
            case "production":
                return Environment.PRODUCTION;
            case "dev":
                // falling forward
            case "devel":
                // falling forward
            case "develop":
                // falling forward
            case "development":
                return Environment.DEVELOPMENT;
            case "test":
                // falling forward
            case "testing":
                return Environment.TESTING;
            default:
                throw new IllegalArgumentException(String.format("Invalid environment name: %s", environment));
        }
    }
}
