package com.csn;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldLauncher {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = buildUsingResourceConfig();
        Server server = new Server(8080);
        server.setHandler(handler);
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(HelloWorldLauncher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }

    static ServletContextHandler buildUsingResourceConfig() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(HelloWorld.class);
        resourceConfig.register(BookController.class); // Добавляем обработчик книг
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/api/*");
        return handler;
    }
}
