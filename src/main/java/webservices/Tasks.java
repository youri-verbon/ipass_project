package webservices;

import model.Task;
import model.User;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/tasks")
public class Tasks {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks(){
        System.out.println(Task.getAllTasks());
        return Response.ok(Task.getAllTasks()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(TaskRequest task){
        List<Task> allTasks = Task.getAllTasks();
        for (Task t : allTasks){
            if(t.getName().equals(task.name)){
                return Response.status(409)
                        .entity(new AbstractMap.SimpleEntry<>("error", "Task name already exists!"))
                        .build();
            }
        }
        Task newTask = new Task(task.name, task.description, task.date);
        return Response.ok(newTask).build();
    }
}
