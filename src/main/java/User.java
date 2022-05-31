import java.util.ArrayList;
import java.util.List;

public class User extends Person{
    private List<Task> assignedTasks = new ArrayList<>();
    private List<Task> completedTasks = new ArrayList<>();

    private static List<User> users = new ArrayList<>();

    public User(String name, String email) {
        super(name, email);
    }
}
