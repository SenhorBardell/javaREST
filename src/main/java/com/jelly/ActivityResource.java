package com.jelly;

import com.jelly.model.Activity;
import com.jelly.model.User;
import com.jelly.repositiory.ActivityRepository;
import com.jelly.repositiory.ActivityRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("activities")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ActivityResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @GET
    public List<Activity> getAllActivities() {
        return activityRepository.findAllActivities();
    }

    @GET
    @Path("{activityId}")
    public Response getActivity(@PathParam("activityId") String activityId) {
        if (activityId == null || activityId.length() < 2) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Activity activity = activityRepository.findActivity(activityId);

        if (activity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(activity).build();
    }

    @GET
    @Path("{activityId}/user")
    public User getActivityUser(@PathParam("activityId") String activityId) {
        return activityRepository.findActivity(activityId).getUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Activity createActivity(Activity activity) {
        System.out.println(activity.getDescription());
        System.out.println(activity.getDuration());


        activityRepository.create(activity);

        return activity;
    }

    @PUT
    @Path("{activityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateActivity(Activity activity) {
        activity = activityRepository.update(activity);

        return Response.ok().entity(activity).build();
    }

    @DELETE
    @Path("{activityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("activityId") String activityId) {
        activityRepository.delete(activityId);

        return Response.ok().build();
    }
}

