package com.jelly.client;

import com.jelly.model.Activity;
import com.jelly.model.ActivitySearch;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

class ActivitySearchClient {

    private Client client;

    ActivitySearchClient() {
        client = ClientBuilder.newClient();
    }

    List<Activity> search(String param, List<String> searchValues, String secondParam, int durationFrom, String thirdParam, int durationTo) {
        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi")
                .path("search/activities")
                .queryParam(param, searchValues)
                .queryParam(secondParam, durationFrom)
                .queryParam(thirdParam, durationTo)
                .build();

        WebTarget target = client.target(uri);

        return target.request().get(new GenericType<List<Activity>>() {});
    }

    List<Activity> search(ActivitySearch search) {
        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi").path("search/activities").build();
        WebTarget target = client.target(uri);
        Response response = target.request().post(Entity.entity(search, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(new GenericType<List<Activity>>() {});
    }
}
