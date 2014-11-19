package ru.yandex.autoschool.splinter.service;

import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static java.lang.String.format;
import static java.nio.file.Files.createTempDirectory;

/**
 * @author eroshenkoam
 * @version %I%, %G%
 * @since 1.0
 */
@Provider
public class DatabaseProvider implements ContainerRequestFilter {
    private static final String DBUSER = "sa";
    private final static Logger logger = LoggerFactory.getLogger(DatabaseProvider.class);
    private static String dbUrl;

    static {
        try {
            dbUrl = format("jdbc:h2:file:%s/%s,user=%s", getDbPath(), getDbName(), DBUSER);
            logger.info(format("Starting embedded database with url '%s' ...", dbUrl));
            openConnection();
            Flyway flyway = new Flyway();
            flyway.setDataSource(dbUrl, DBUSER, null);
            flyway.migrate();
        } catch (Exception e) {
            logger.error("Failed to start embedded database", e);
            System.exit(-1);
        }
    }

    public static void openConnection() {
        if (!Base.hasConnection()) {
            Base.open(org.h2.Driver.class.getName(), dbUrl, DBUSER, "");
        }
    }

    private static String getDbName() {
        return getProperty("db.name", "default");
    }

    private static String getDbPath() throws IOException {
        return getProperty("db.path", createTempDirectory("blog").toAbsolutePath().toString());
    }

    private static String getProperty(String key, String defaultValue) {
        final String value = System.getProperty(key);
        return (value == null) ? defaultValue : value;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        openConnection();
    }
}