package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Task {
    private String name;
    private String description;
    private String date;

    public static List<Task> allTasks = new ArrayList<>();

    public Task(String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;
        allTasks.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static List<Task> getAllTasks(){
        return Collections.unmodifiableList(allTasks);
    }
}
