package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Context
    HttpServletRequest request;

    public BaseResource() {
        this.ViewData = new ViewData();
    }

    public void flushError() {
        HttpSession session = request.getSession(true);
        String error = (String) session.getAttribute("error");
        if (error != null) {
            this.ViewData.set("error", error);
            session.removeAttribute("error");
        }
    }
}
