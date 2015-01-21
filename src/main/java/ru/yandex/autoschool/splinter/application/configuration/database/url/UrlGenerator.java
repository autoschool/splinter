package ru.yandex.autoschool.splinter.application.configuration.database.url;
//"jdbc:mysql://%s:%d/%s?user=%s&password=%s"

import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class UrlGenerator implements UrlGeneratorInterface {
    public String generateUrl(DatabaseConfiguration configuration) {
        UrlGeneratorInterface concreteGenerator;
        switch (configuration.getDriver()) {
            case H2:
                concreteGenerator = new H2UrlGenerator();
                break;
            case MYSQL:
                concreteGenerator = new MysqlUrlGenerator();
                break;
            default:
                throw new IllegalArgumentException("Unknown database driver");
        }
        return concreteGenerator.generateUrl(configuration);
    }
}
