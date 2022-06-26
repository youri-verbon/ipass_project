package webservices;

import model.Task;
import  model.User;

import javax.annotation.security.RolesAllowed;
import javax.json.JsonObject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

import static com.azure.storage.blob.implementation.models.QueryFormatType.JSON;

@Path("/users")
public class Users {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        System.out.println(User.getUsers());
        return Response.ok(User.getUsers()).build();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserTasks(@PathParam("userId") int id){
        for (User user : User.getUsers()){
            if (user.getUserId() == id){
                return Response.ok(user.getAssignedTasks()).build();
            }
        }
        return Response.status(409)
                .entity(new AbstractMap.SimpleEntry<>("error", "User does not exist!"))
                .build();
    }

    @GET
    @Path("/allUserTasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserTasks()throws IOException {
        Map<String, List> map = new HashMap<>();
        for (User user : User.getUsers()){
            map.put(user.getName(), user.getAssignedTasks());
        }
        return Response.ok(map).build();
    }


    @PATCH
    @Path("{userId}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTaskToUser(@PathParam("userId") int userId, TaskRequest task){
        System.out.println(userId);
        System.out.println(1);
        for (User user : User.getUsers()){
            if (user.getUserId() == userId){
                System.out.println(2);
                for (Task t : Task.getAllTasks()){
//                  Task task = new Task(taskRequest.name, taskRequest.description, taskRequest.date);
                    if (t.getName().equals(task.name)){
                        System.out.println(3);
                        for (Task userTask : user.getAssignedTasks()){
                            if (userTask.getName().equals(task.name)){
                                return Response.status(409)
                                        .entity(new AbstractMap.SimpleEntry<>("error", "User already has this task assigned!"))
                                        .build();
                            }
                        }
                        user.setAssignedTasks(t);
                    }
                }
                return Response.ok(user.getAssignedTasks()).build();
            }
        }
        return Response.status(409)
                .entity(new AbstractMap.SimpleEntry<>("error", "User or task does not exist!"))
                .build();
    }
}
