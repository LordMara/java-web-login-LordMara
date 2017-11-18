package com.codecool.krk.lordmara;

import com.codecool.krk.lordmara.DAO.DatabaseMigration;
import com.codecool.krk.lordmara.controller.RedirectHandler;
import com.codecool.krk.lordmara.controller.StaticHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App
{
    public static void main(String[] args) throws Exception {
        DatabaseMigration.migrateDatbase();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

//        server.createContext("/login", new LoginHandler());
        server.createContext("/static", new StaticHandler());
        server.createContext("/redirect", new RedirectHandler());
        server.setExecutor(null);

        server.start();
    }
}