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
        User henk = new User("Henk", "henk@henk.nl");
        henk.setPassword("wachtwoord");
        henk.setRole("admin");
        new User("Piet", "Piet@piet.nl");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format(date);
        Task badkamer = new Task("Badkamer", "De badkamer moet je schoonmaken met chloor", str);
        henk.setAssignedTasks(badkamer);
        new Task("Keuken", "De keuken moet je schoonmaken met allesreiniger", str);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
