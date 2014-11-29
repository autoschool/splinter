package ru.yandex.autoschool.splinter.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;

/**
 * Created by pacahon on 29.11.14.
 */
@ErrorTemplate(name = "/templates/error.ftl")
public class BaseResource {

    public HashMap viewData;

    @Context
    SecurityContext securityContext;

    public BaseResource() {

        this.viewData = new HashMap();
        /* FIXME Можно ли вообще как-то сюда прокинуть securityContext?
        * Чтобы каждый раз перед рендерингом шаблона не писать this.viewData.put('authUser', securityContext.getUserPrincipal());
        * */

    }

}
