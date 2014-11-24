package ru.yandex.autoschool.splinter.config.database.url;

import static java.lang.String.format;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class H2UrlGenerator {
    public static String getInMemoryDbUrl(String dbName) {
        return format("jdbc:h2:mem:%s", dbName);
    }
    public static String getInMemoryDbUrl(String dbName, String user) {
        if (user == null) {
            return getInMemoryDbUrl(dbName);
        }
        return format("jdbc:h2:mem:%s,user=%s", dbName, user);
    }
    public static String getInMemoryDbUrl(String dbName, String user, String password) {
        if (password == null) {
            return getInMemoryDbUrl(dbName, user);
        }
        return format("jdbc:h2:mem:%s,user=%s,password=%s", dbName, user, password);
    }
    public static String getNetworkDbUrl(String host, String dbPath) {
        return format("jdbc:h2:tcp://%s/%s", host, dbPath);
    }
    public static String getNetworkDbUrl(String host, int port, String dbPath) {
        if (port <= 0) {
            getNetworkDbUrl(host, dbPath);
        }
        return format("jdbc:h2:tcp://%s:%d/%s", host, port, dbPath);
    }
    public static String getNetworkDbUrl(String host, int port, String dbPath, String user) {
        if (user == null) {
            return getNetworkDbUrl(host, port, dbPath);
        }
        return format("jdbc:h2:tcp://%s:%d/%s,user=%s", host, port, dbPath, user);
    }
    public static String getNetworkDbUrl(String host, int port, String dbPath, String user, String password) {
        if (password == null) {
            getNetworkDbUrl(host, port, dbPath, user);
        }
        return format("jdbc:h2:tcp://%s:%d/%s,user=%s,password=%s", host, port, dbPath, user, password);
    }
    public static String getFileDbUrl(String dbPath) {
        return format("jdbc:h2:file:%s", dbPath);
    }
    public static String getFileDbUrl(String dbPath, String user) {
        if (user == null) {
            return getFileDbUrl(dbPath);
        }
        return format("jdbc:h2:file:%s,user=%s", dbPath, user);
    }
    public static String getFileDbUrl(String dbPath, String user, String password) {
        if (password == null) {
            return getFileDbUrl(dbPath, user);
        }
        return format("jdbc:h2:file:%s,user=%s,password=%s", dbPath, user, password);
    }
}
