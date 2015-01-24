package ru.yandex.autoschool.splinter.application.configuration.database.url;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.yandex.autoschool.splinter.application.configuration.DatabaseConfiguration;
import ru.yandex.autoschool.splinter.application.configuration.database.Driver;
import ru.yandex.autoschool.splinter.application.configuration.database.Location;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static junitparams.JUnitParamsRunner.$;

@SuppressWarnings("UnusedDeclaration")
@RunWith(JUnitParamsRunner.class)
public class MysqlUrlGeneratorTest {
    private DatabaseConfiguration generateConfigurationMock(
            Location location,
            String name,
            String user,
            String password,
            String host,
            Integer port,
            String path
    ) {
        DatabaseConfiguration mock = mock(DatabaseConfiguration.class);
        stub(mock.getDriver()).toReturn(Driver.MYSQL);
        stub(mock.getLocation()).toReturn(location);
        stub(mock.getName()).toReturn(name);
        stub(mock.getHost()).toReturn(host);
        stub(mock.getPort()).toReturn(port);
        stub(mock.getPath()).toReturn(path);
        stub(mock.getUser()).toReturn(user);
        stub(mock.getPassword()).toReturn(password);
        return mock;
    }
    
    public Object[] validDataProvider() {
        return $(
                $(
                        Location.NETWORK,
                        "dummyname",
                        "dummyuser",
                        "dummypassword",
                        "dummyhost",
                        1337,
                        "dummypath",
                        "jdbc:mysql://dummyhost:1337/dummyname?user=dummyuser&password=dummypassword"
                ),
                $(
                        Location.NETWORK,
                        "dummyName",
                        null,
                        null,
                        "dummyHost",
                        null,
                        null,
                        "jdbc:mysql://dummyHost/dummyName"
                )
        );
    }
    
    public Object[] invalidDataProvider() {
        return $(
                $(
                        Location.FILE,
                        "dummyname",
                        "dummyuser",
                        "dummypass",
                        "dummyhost",
                        1337,
                        "dummypath"
                ),
                $(
                        Location.NETWORK,
                        "dummyname",
                        "dummyuser",
                        "dummypass",
                        null,
                        1337,
                        "dummypath"
                ),
                $(
                        Location.NETWORK,
                        null,
                        "dummyuser",
                        "dummypass",
                        "dummyhost",
                        1337,
                        "dummypath"
                )
        );
    }

    @SuppressWarnings("TestMethodWithIncorrectSignature")
    @Test
    @Parameters(method = "validDataProvider")
    public void shouldGenerateCorrectUrl(
            Location location,
            String name,
            String user,
            String password,
            String host,
            Integer port,
            String path,
            String expectedUrl
    ) {
        DatabaseConfiguration mock = generateConfigurationMock(
                location,
                name,
                user,
                password,
                host,
                port,
                path
        );
        MysqlUrlGenerator generator = new MysqlUrlGenerator();
        assertEquals(expectedUrl, generator.generateUrl(mock));
    }

    @SuppressWarnings("TestMethodWithIncorrectSignature")
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "invalidDataProvider")
    public void shouldGenerateIncorrectDataException(
            Location location,
            String name,
            String user,
            String password,
            String host,
            Integer port,
            String path
    ) {
        DatabaseConfiguration mock = generateConfigurationMock(
                location,
                name,
                user,
                password,
                host,
                port,
                path
        );
        new MysqlUrlGenerator().generateUrl(mock);
    }
}
