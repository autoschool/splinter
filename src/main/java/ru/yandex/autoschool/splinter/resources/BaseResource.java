package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import ru.yandex.autoschool.splinter.view.freemarker.ViewData;

/**
 * Created by pacahon on 29.11.14.
 */
@ErrorTemplate(name = "/error.ftl")
public class BaseResource {

    public ViewData ViewData;

    @Context
    SecurityContext securityContext;

    public BaseResource() {
        this.ViewData = new ViewData();
    }
}
