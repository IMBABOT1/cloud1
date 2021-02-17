package ru.geekbrains.cloud.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStart {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    Server server = context.getBean("server", Server.class);
    server.run();
}
