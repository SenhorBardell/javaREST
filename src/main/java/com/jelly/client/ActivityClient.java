package com.jelly.client;

import com.jelly.model.Activity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

class ActivityClient {

    private Client client;

    ActivityClient() {
        client = ClientBuilder.newClient();
    }

    Activity get(String id) {
        WebTarget target = client.target("http://localhost:8080/webapi/");
        Response response = target.path("activities/" + id).request().get(Response.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server");
        }

        return response.readEntity(Activity.class);
    }

    List<Activity> get() {
        WebTarget target = client.target("http://localhost:8080/webapi/");

        List<Activity> response = target.path("activities").request()
                .get(new GenericType<List<Activity>>() {});

        return response;
    }

    Activity create(Activity activity) {
        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("activities").request()
                .post(Entity.entity(activity, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server");
        }

        return response.readEntity(Activity.class);
    }

    Activity update(Activity activity) {
        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("activities/" + activity.getId()).request()
                .put(Entity.entity(activity, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server");
        }

        return response.readEntity(Activity.class);
    }

    void delete(String activityId) {
        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("activities/" + activityId).request().delete();

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server");
        }
    }
}
