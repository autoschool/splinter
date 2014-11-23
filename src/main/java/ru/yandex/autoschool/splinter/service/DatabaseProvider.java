package ru.yandex.autoschool.splinter.service;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.apache.log4j.BasicConfigurator;
import org.h2.jdbcx.JdbcDataSource;
import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataSource;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.sql.Connection;
import java.util.Set;

import static java.lang.String.format;
import static java.nio.file.Files.createTempDirectory;


/**
 * @author eroshenkoam
 * @version %I%, %G%
 * @since 1.0
 */
@Provider
public class DatabaseProvider implements ContainerRequestFilter {
    private final static String DBUSER = "sa";
    private final static Logger logger = LoggerFactory.getLogger(DatabaseProvider.class);
    private static String dbUrl;

    static {
        BasicConfigurator.configure();
        try {
            dbUrl = format("jdbc:h2:mem:%s,user=%s", getDbName(), DBUSER);
            logger.info(format("Starting embedded database with url '%s' ...", dbUrl));
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL(dbUrl);
            dataSource.setUser(DBUSER);
            Connection connection = dataSource.getConnection();
            JdbcConnection liquibaseConnection = new JdbcConnection(connection);
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
            String changeLogPath = getChangeLogLocation();
            logger.info(format("Using `%s` as changelog path", changeLogPath));
            Liquibase liquibase = new Liquibase(changeLogPath, resourceAccessor, liquibaseConnection);
            liquibase.update("");
            openConnection();
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

    private static String getChangeLogLocation() {
        String path = DatabaseProvider.class.getClassLoader().getResource("/db/migration/changelog.xml").toString();
        return path == null ? null : path.substring(5);
    }

    private static String getDbName() {
        return getProperty("db.name", "splinter");
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