package setup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.Task;
import model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@WebListener
public class MyServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new User("Henk", "henk@henk.nl");
        new User("Piet", "Piet@piet.nl");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        new Task("Badkamer", "De badkamer moet je schoonmaken met chloor", str);
        new Task("Keuken", "De keuken moet je schoonmaken met allesreiniger", str);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
