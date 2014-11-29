package ru.yandex.autoschool.splinter.config.database.url;

import static java.lang.String.format;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class MysqlUrlGenerator {
    private MysqlUrlGenerator() {}
    public static String getNetworkDbUrl(String host, String dbName) {
        return format("jdbc:mysql://%s/%s", host, dbName);
    }
    public static String getNetworkDbUrl(String host, int port, String dbName) {
        if (port <= 0) {
            getNetworkDbUrl(host, dbName);
        }
        return format("jdbc:mysql://%s:%d/%s", host, port, dbName);
    }
    public static String getNetworkDbUrl(String host, int port, String dbName, String user) {
        if (user == null) {
            return getNetworkDbUrl(host, port, dbName);
        }
        return format("jdbc:mysql://%s:%d/%s?user=%s", host, port, dbName, user);
    }
    public static String getNetworkDbUrl(String host, int port, String dbName, String user, String password) {
        if (password == null) {
            getNetworkDbUrl(host, port, dbName, user);
        }
        return format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", host, port, dbName, user, password);
    }
}
