package com.codecool.krk.lordmara;

import com.codecool.krk.lordmara.DAO.DatabaseMigration;
import com.codecool.krk.lordmara.controller.LoginHandler;
import com.codecool.krk.lordmara.controller.StaticHandler;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        DatabaseMigration.migrateDatabase();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/login", new LoginHandler());
        server.createContext("/static", new StaticHandler());
//        server.createContext("/logout", new LogoutHandler());
        server.setExecutor(null);

        server.start();
    }
}