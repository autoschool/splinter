package ru.yandex.autoschool.splinter.application.configuration.database.url;

import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
import ru.yandex.autoschool.splinter.application.configuration.database.Location;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class MysqlUrlGenerator implements UrlGeneratorInterface {
    final public String URL_FORMAT = "jdbc:mysql://%s/%s";
    @Override
    public String generateUrl(DatabaseConfiguration configuration) throws IllegalArgumentException {
        validateConfiguration(configuration);
        String host = configuration.getHost();
        if (configuration.getPort() != null) {
            host += ':' + configuration.getPort().toString();
        }
        String queryString = generateQueryString(configuration);
        String url = String.format(URL_FORMAT, host, configuration.getName());
        if (queryString != null) {
            url += '?' + queryString;
        }
        return url;
    }
    protected String generateQueryString(DatabaseConfiguration configuration) {
        String queryString = null;
        if (configuration.getUser() != null) {
            queryString = "user=" + configuration.getUser();
            if (configuration.getPassword() != null) {
                queryString += "&password=" + configuration.getPassword();
            }
        }
        return queryString;
    }
    protected void validateConfiguration(DatabaseConfiguration configuration) {
        if (configuration.getLocation() != Location.NETWORK) {
            throw new IllegalArgumentException("MySQL doesn't support anything but network URL generation");
        }
        if (configuration.getHost() == null) {
            throw new IllegalArgumentException("MySQL URL generation requires database host to be specified");
        }
        if (configuration.getName() == null) {
            throw new IllegalArgumentException("MySQL URL generation requires database name to be specified");
        }
    }
}
