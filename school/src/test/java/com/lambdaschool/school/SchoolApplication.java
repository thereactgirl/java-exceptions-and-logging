package com.lambdaschool.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;

//ENABLE MY OWN ROUTING
//@EnableWebMvc
//@EnableJpaAuditing comment this out for testing to work
@SpringBootApplication
public class SchoolApplication
{

    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(SchoolApplication.class,
                args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
