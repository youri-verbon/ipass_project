package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private List<Task> assignedTasks = new ArrayList<>();
    private List<Task> completedTasks = new ArrayList<>();
    private static int idCounter = 0;

    private int userId;
    private String name;
    private String email;

    private static List<User> users = new ArrayList<>();

    public User(String name, String email) {
        this.userId = generateId();
        this.name = name;
        this.email = email;
        users.add(this);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int generateId(){
        return idCounter++;
    }

    public void setAssignedTasks(Task task){
        assignedTasks.add(task);
    }
    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public static List<User> getUsers(){
        return Collections.unmodifiableList(users);
    }
}
