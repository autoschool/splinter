package ru.yandex.autoschool.splinter.interceptions;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import javax.annotation.Priority;

import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import ru.yandex.autoschool.splinter.models.User;
import ru.yandex.autoschool.splinter.view.freemarker.ViewData;

/**
 * Created by pacahon on 07.12.14.
 * Перехватываем объект Viewable и подменяем модель ViewData  на обычный HashMap
 */

/** FIXME: Возможно можно переписать на свой Entity Provider
 * Но это геморнее, т.к. новый провайдер фактически будет дублировать
 * ViewableMessageBodyWriter.class
 * https://jersey.java.net/documentation/latest/message-body-workers.html
 *
 **/
@Priority(Priorities.USER)
public class UserDataInterceptor implements WriterInterceptor {
    @Context
    SecurityContext securityContext;
    
    @Inject
    private Logger logger;

    @Override
    public void aroundWriteTo(final WriterInterceptorContext context) throws IOException, WebApplicationException {
        final Object entity = context.getEntity();
        if (entity instanceof Viewable) {
            User user = (User) securityContext.getUserPrincipal();

            if ( ((Viewable) entity).getModel() instanceof ViewData) {
                ViewData model = ((ViewData) ((Viewable) entity).getModel());
                model.set("authUser", user);
                String templateName = ((Viewable) entity).getTemplateName();
                context.setEntity(new Viewable(templateName, model.getData()));
            }
        }

        context.proceed();
    }
}
