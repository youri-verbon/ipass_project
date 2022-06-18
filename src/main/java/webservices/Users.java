package webservices;

import model.Task;
import  model.User;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

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


    @PATCH
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTaskToUser(@PathParam("userId") int userId, String taskName){
        for (User user : User.getUsers()){
            if (user.getUserId() == userId){
                for (Task t : Task.getAllTasks()){
//                  Task task = new Task(taskRequest.name, taskRequest.description, taskRequest.date);
                    if (t.getName().equals(taskName)){
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
