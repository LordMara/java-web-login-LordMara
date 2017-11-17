package com.codecool.krk.lordmara.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class RedirectHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set("Location", "/");
        httpExchange.sendResponseHeaders(302,-1);

    }

}
