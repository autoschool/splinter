package ru.yandex.autoschool.splinter.IndexResources;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import ru.yandex.autoschool.splinter.resources.IndexResource;

import javax.ws.rs.core.Application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by maksim on 24.11.14.
 */
public class AboutPageTest extends JerseyTest {
    @Override
    protected Application configure(){
        return new ResourceConfig(IndexResource.class);
    }

    @Test
    public void testAboutPage(){
        final String about = target("/about").request().get(String.class);
        assertThat(about,is("about page"));


    }
}
