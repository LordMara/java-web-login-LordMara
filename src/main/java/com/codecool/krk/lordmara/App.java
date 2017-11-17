package com.codecool.krk.lordmara;

import com.codecool.krk.lordmara.controller.Static;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App
{
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/static", new Static());
        server.setExecutor(null);

        server.start();
    }
}