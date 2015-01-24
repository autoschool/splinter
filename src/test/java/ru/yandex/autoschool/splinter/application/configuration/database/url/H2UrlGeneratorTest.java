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
public class H2UrlGeneratorTest {

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

    // @todo requires more data samples
    public Object[] validDataProvider() {
        return $(
                $(
                        Location.NETWORK,
                        "dummyname",
                        "dummyuser",
                        "dummypassword",
                        "dummyhost",
                        1337,
                        "dummydir/dummydir",
                        "jdbc:h2:tcp://dummyhost:1337/dummydir/dummydir/dummyname;user=dummyuser,password=dummypassword"
                ),
                $(
                        Location.FILE,
                        "dummyName",
                        null,
                        null,
                        null,
                        null,
                        "dummy/dummy/path",
                        "jdbc:h2:file:dummy/dummy/path/dummyName"
                ),
                $(
                        Location.MEMORY,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        "jdbc:h2:mem:"
                ),
                $(
                        Location.MEMORY,
                        "dummyName",
                        null,
                        null,
                        null,
                        null,
                        null,
                        "jdbc:h2:mem:dummyName"
                )
        );
    }

    // @todo more data samples
    public Object[] invalidDataProvider() {
        return $(
                $(
                        Location.FILE,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
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
                        null
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
        assertEquals(expectedUrl, new H2UrlGenerator().generateUrl(mock));
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
        new H2UrlGenerator().generateUrl(mock);
    }
}
