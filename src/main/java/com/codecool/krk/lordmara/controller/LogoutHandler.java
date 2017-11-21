package com.codecool.krk.lordmara.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LogoutHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        LoginHandler.getUsersSessions().remove(cookieStr);

        String cookie = "sessionId=\"\"; max-age=0;";
        httpExchange.getResponseHeaders().add("Set-Cookie",cookie);

        httpExchange.getResponseHeaders().set("Location", "/login");
        httpExchange.sendResponseHeaders(302, -1);
    }

}


