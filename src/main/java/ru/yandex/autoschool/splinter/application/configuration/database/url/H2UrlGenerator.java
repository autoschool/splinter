package ru.yandex.autoschool.splinter.application.configuration.database.url;

import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
import ru.yandex.autoschool.splinter.application.configuration.database.Location;

//"jdbc:h2:tcp://%s:%d/%s,user=%s,password=%s",
//"jdbc:h2:mem:%s,user=%s,password=%s"
/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class H2UrlGenerator implements UrlGeneratorInterface {
    final public static String URL_FORMAT = "jdbc:h2:%s";
    @Override
    public String generateUrl(DatabaseConfiguration configuration) {
        validateConfiguration(configuration);
        String url = String.format(URL_FORMAT, generateLocationPart(configuration));
        if (configuration.getUser() != null) {
            url += ";user=" + configuration.getUser();
            if (configuration.getPassword() != null) {
                url += ",password=" + configuration.getPassword();
            }
        }
        return url;
    }
    protected String generateLocationPart(DatabaseConfiguration configuration) {
        switch (configuration.getLocation()) {
            case MEMORY:
                return generateMemoryLocationPart(configuration);
            case FILE:
                return generateFileLocationPart(configuration);
            case NETWORK:
                return generateNetworkLocationPart(configuration);
            default:
                throw new IllegalArgumentException("Unknown database location");
        }
    }
    protected String generateMemoryLocationPart(DatabaseConfiguration configuration) {
        String part = "mem:";
        if (configuration.getName() != null) {
            part += configuration.getName();
        }
        return part;
    }
    protected String generateFileLocationPart(DatabaseConfiguration configuration) {
        if (configuration.getPath() != null && configuration.getName() != null) {
            return String.format("file:%s/%s", configuration.getPath(), configuration.getName());
        }
        if (configuration.getPath() != null) {
            return "file:" + configuration.getPath();
        }
        return "file:" + configuration.getName();
    }
    protected String generateNetworkLocationPart(DatabaseConfiguration configuration) {
        String url = "tcp://" + configuration.getHost();
        if (configuration.getPort() != null) {
            url += ':' + configuration.getPort().toString();
        }
        url += '/';
        if (configuration.getPath() != null) {
            url += configuration.getPath();
        }
        if (configuration.getName() != null) {
            if (configuration.getPath() != null && url.charAt(url.length() - 1) != '/') {
                url += '/';
            }
            url += configuration.getName();
        }
        return url;
    }
    protected void validateConfiguration(DatabaseConfiguration configuration) {
        if (configuration.getLocation() == Location.FILE && configuration.getName() == null
                && configuration.getPath() == null) {
            throw new IllegalArgumentException(
                    "Either database path or database name is required for file H2 JDBC URL generation"
            );
        }
        if (configuration.getLocation() == Location.NETWORK) {
            if (configuration.getHost() == null) {
                throw new IllegalArgumentException("Database host is required for network H2 JDBC URL generation");
            }
            if (configuration.getPath() == null && configuration.getName() == null) {
                throw new IllegalArgumentException(
                        "Either database path or database name is required for network H2 JDBC URL generation"
                );
            }
        }
    }
}
