package com.codecool.krk.lordmara.controller;

import com.codecool.krk.lordmara.DAO.UserDAO;
import com.codecool.krk.lordmara.model.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.*;

public class LoginHandler implements HttpHandler {
    private Map<String, Integer> usersSessions = new HashMap<>();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            userContent(cookieStr, httpExchange);
            System.out.println(cookieStr);
        } else {
            login(httpExchange);
        }
    }

    private void login(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            List<String> loginData = parseLoginFormData(formData);

            String userName = loginData.get(0);
            String password = loginData.get(1);

            User user = UserDAO.getInstance().getUser(userName, password);

            if (user != null) {
                createCookie(user.getId(), httpExchange);
                httpExchange.getResponseHeaders().set("Location", "/login");
                httpExchange.sendResponseHeaders(302,-1);
            }
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login.twig");
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void userContent(String cookieStr, HttpExchange httpExchange) throws IOException {
        Integer userId = usersSessions.get(cookieStr);

        User user = UserDAO.getInstance().getUser(userId);

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/home.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("user", user);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void createCookie(Integer userId, HttpExchange httpExchange) throws IOException {
        HttpCookie cookie = new HttpCookie("sessionId", UUID.randomUUID().toString());
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        usersSessions.put(cookie.toString(), userId);
    }

    private List<String> parseLoginFormData(String formData) throws UnsupportedEncodingException {
        List<String> userLoginData = new ArrayList<>();
        String userName;
        String password;
        String[] pairs = formData.split("&");

        try {
            userName = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            password = new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");

            userLoginData.add(userName);
            userLoginData.add(password);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return userLoginData;
    }

}
