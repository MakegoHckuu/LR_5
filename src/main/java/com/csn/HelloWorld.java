package com.csn;

import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HelloWorld extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest,
                       jakarta.servlet.http.HttpServletRequest request,
                       jakarta.servlet.http.HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String username = request.getParameter("username");

        String responseHtml;
        if (username != null && !username.isEmpty()) {
            responseHtml = "<h1>Hello, " + username + "!</h1>";
        } else {
            responseHtml = "<h1>Enter your name</h1>" +
                    "<form method='GET'>" +
                    "<input type='text' name='username' required>" +
                    "<button type='submit'>Submit</button>" +
                    "</form>";
        }

        baseRequest.setHandled(true);
        response.getWriter().println(responseHtml);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new HelloWorld());
        server.start();
        server.join();
    }
}
