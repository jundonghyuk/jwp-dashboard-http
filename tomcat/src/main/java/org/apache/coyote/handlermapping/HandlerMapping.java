package org.apache.coyote.handlermapping;

import org.apache.coyote.handler.Handler;
import org.apache.coyote.handler.dynamichandler.LoginHandler;
import org.apache.coyote.handler.dynamichandler.RegisterHandler;
import org.apache.coyote.handler.statichandler.*;
import org.apache.coyote.http11.HttpMethod;
import org.apache.coyote.http11.HttpStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerMapping {

    private static Map<HandlerMatcher, Handler> handlerMap = new ConcurrentHashMap<>() {
        {
            put(new HandlerMatcher(HttpMethod.GET, "/"), new HelloHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/index.html"), new HtmlHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/assets/chart-area.js"), new JsHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/assets/chart-bar.js"), new JsHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/assets/chart-pie.js"), new JsHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/js/scripts.js"), new JsHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/css/styles.css"), new CssHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/login"), new LoginHandler());
            put(new HandlerMatcher(HttpMethod.POST, "/login"), new LoginHandler());
            put(new HandlerMatcher(HttpMethod.GET, "/register"), new RegisterHandler());
            put(new HandlerMatcher(HttpMethod.POST, "/register"), new RegisterHandler());
        }
    };

    public static boolean canHandle(HandlerMatcher handlerMatcher) {
        System.out.println(handlerMatcher.getHttpMethod() + " " + handlerMatcher.getRequestTarget() + "  " + handlerMap.containsKey(handlerMatcher));
        if (handlerMap.containsKey(handlerMatcher)) {
            return true;
        }
        return false;
    }

    public static Handler getHandler(HandlerMatcher handlerMatcher) {
        return handlerMap.get(handlerMatcher);
    }

    public static Handler getExceptionHandler(HttpStatus httpStatus) {
        return new ExceptionHandler(httpStatus);
    }
}
