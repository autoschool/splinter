package ru.yandex.autoschool.splinter.PostResources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.autoschool.splinter.resources.PostResource;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * autor:maksim
 * date: 25.01.15
 * time: 15:13.
 */
public class PostTest extends JerseyTest {
    String title;
    String content;
    Response expectedResponse;
    Client client;
    WebResource webResource;
    @Before
    public void init(){
        client=Client.create();
        webResource=client.resource("http://localhost:8080/posts/new");
        title="test title";
        content="test content";
        expectedResponse=Response.ok().build();
    }
    @Override
    protected Application configure(){
        return new ResourceConfig(PostResource.class);
    }
    @Test
    public void shouldBeCreated(){
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("title", title);
        formData.add("content", content);
        ClientResponse actualResponse = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, formData);
        assertThat(expectedResponse.getStatus(),is(actualResponse.getStatus()));
    }

}