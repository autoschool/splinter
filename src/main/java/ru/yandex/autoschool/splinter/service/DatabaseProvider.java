package ru.yandex.autoschool.splinter.service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import org.h2.jdbcx.JdbcDataSource;
import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.application.Configuration;
import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
import ru.yandex.autoschool.splinter.application.configuration.database.Driver;
import ru.yandex.autoschool.splinter.config.FilterPriorities;
import ru.yandex.autoschool.splinter.di.SimpleContainer;
import ru.yandex.autoschool.splinter.utils.db.Initializer;

import javax.annotation.Priority;
import javax.sql.DataSource;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import static java.lang.String.format;


/**
 * @author eroshenkoam
 * @version %I%, %G%
 * @since 1.0
 */
@Priority(FilterPriorities.DBCONNECTION)
@Provider
@SuppressWarnings("unused")
@edu.umd.cs.findbugs.annotations.SuppressWarnings(
        value = "org.sonar.java.checks.SystemExitCalledCheck",
        justification = "Halts whole application in case database is not available and there's no point in going further"
)
public class DatabaseProvider implements ContainerRequestFilter {
    private static String dbUrl;
    private static Driver dbDriver;
    private static String dbUser;
    private static String dbPassword;

    static {
        Configuration configuration = SimpleContainer.getApplication().getConfiguration();
        DatabaseConfiguration databaseConfiguration = configuration.getDatabaseConfiguration();
        Logger logger = SimpleContainer.getLogger();
        try {
            dbUrl = configuration.getDatabaseUrl();
            dbDriver = databaseConfiguration.getDriver();
            dbUser = databaseConfiguration.getUser();
            dbPassword = databaseConfiguration.getPassword();

            logger.info(format("Starting embedded database with url '%s' ...", dbUrl));
            String changeLogPath = getChangeLogLocation();
            logger.info(format("Using `%s` as changelog path", changeLogPath));
            Liquibase liquibaseMigrationManager = new Liquibase(
                    changeLogPath, new FileSystemResourceAccessor(), getLiquibaseConnection()
            );
            liquibaseMigrationManager.update("");
            openConnection();
            Initializer.initialize();
        } catch (Exception e) {
            logger.error("Failed to start embedded database", e);
            System.exit(-1);
        }
    }
    private static DataSource getDataSource(Driver driver, String url, String user, String password) {
        DataSource dataSource;
        switch (driver) {
            case H2:
                JdbcDataSource h2DataSource = new JdbcDataSource();
                h2DataSource.setUrl(url);
                user = user != null ? user : "";
                password = password != null ? password : "";
                h2DataSource.setUser(user);
                h2DataSource.setPassword(password);
                return h2DataSource;
            case MYSQL:
                MysqlDataSource mysqlDataSource = new MysqlDataSource();
                mysqlDataSource.setURL(url);
                mysqlDataSource.setUser(user);
                mysqlDataSource.setPassword(password);
                return mysqlDataSource;
            default:
                throw new IllegalArgumentException("Unknown database driver");
        }
    }

    public static void openConnection() {
        if (!Base.hasConnection()) {
            String user = dbUser != null ? dbUser : "";
            String password = dbPassword != null ? dbPassword : "";
            switch (dbDriver) {
                case MYSQL:
                    Base.open(com.mysql.jdbc.Driver.class.getName(), dbUrl, user, password);
                    break;
                case H2:
                    Base.open(org.h2.Driver.class.getName(), dbUrl, user, password);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown database driver");
            }
        }
    }

    private static String getChangeLogLocation() {
        URL url = DatabaseProvider.class.getClassLoader().getResource("/db/migration/changelog.xml");
        return (url == null) ? null : url.getFile();
    }

    private static String getFixturesLocation() {
        URL url = DatabaseProvider.class.getClassLoader().getResource("/db/fixtures/fixtures.xml");
        return (url == null) ? null : url.getFile();
    }

    private static JdbcConnection getLiquibaseConnection() throws SQLException {
        DataSource dataSource = getDataSource(dbDriver, dbUrl, dbUser, dbPassword);
        return new JdbcConnection(dataSource.getConnection());
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        openConnection();
    }
}
