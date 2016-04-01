package com.jelly;

import com.jelly.model.Activity;
import com.jelly.model.ActivitySearch;
import com.jelly.repositiory.ActivityRepositoryStub;
import com.jelly.repositiory.ActivityRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("search/activities")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ActivitySearchResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @GET
    public Response searchForActivities(
            @QueryParam(value = "description") List<String> descriptions,
            @QueryParam(value = "durationFrom") int durationFrom,
            @QueryParam(value = "durationTo") int durationTo
    ) {
        List<Activity> activities = activityRepository.findByDescription(descriptions, durationFrom, durationTo);

        if (activities == null || activities.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
    }

    @POST
    public Response searchForActivities(ActivitySearch search) {
        List<Activity> activities = activityRepository.findByConstraints(search);

        if (activities == null || activities.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
    }
}
