package ru.yandex.autoschool.splinter.application;

public enum Environment {
    PRODUCTION,
    DEVELOPMENT,
    TESTING;
    
    public static String getShortName(Environment environment) {
        switch (environment) {
            case PRODUCTION:
                return "prod";
            case DEVELOPMENT:
                return "dev";
            case TESTING:
                return "test";
            default:
                throw new IllegalArgumentException("Unknown environment (null?)");
        }
    } 
}
