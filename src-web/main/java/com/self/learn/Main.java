package com.self.learn;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) throws LifecycleException, ServletException {
        TomcatServer.setUp().setUpApplicationContext().startUp();
    }

    private static class TomcatServer {

        private static Logger logger = Logger.getAnonymousLogger();

        private static final Tomcat tomcat = new Tomcat();


        static TomcatServer setUp() throws ServletException {
            tomcat.setPort(8080);
            Context ctx = tomcat.addWebapp("/", new File("web").getAbsolutePath());
            logger.log(Level.INFO, new File("src/main/resources").getAbsolutePath());
            return new TomcatServer();
        }

        TomcatServer setUpApplicationContext() {
            ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("core-config.xml");
            context.refresh();
            return this;
        }

        void startUp() throws LifecycleException {
            tomcat.start();
            tomcat.getServer().await();
        }


    }
}



