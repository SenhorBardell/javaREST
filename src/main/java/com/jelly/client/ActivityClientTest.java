package com.jelly.client;

import com.jelly.model.Activity;
import com.jelly.model.ActivitySearch;
import com.jelly.model.ActivitySearchType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ActivityClientTest {

    @Test
    public void testSearchObject() {
        ActivitySearchClient client = new ActivitySearchClient();
        List<String> searchValues = new ArrayList<String>();
        searchValues.add("biking");
        searchValues.add("hiking");

        ActivitySearch search = new ActivitySearch();
        search.setDescriptions(searchValues);
        search.setDurationFrom(30);
        search.setDurationTo(55);
        search.setSearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);

        List<Activity> activities = client.search(search);
        assertNotNull(activities);
    }

    @Test
    public void testSearch() {
        ActivitySearchClient client = new ActivitySearchClient();

        String param = "description";
        List<String> searchValues = new ArrayList<>();
        searchValues.add("swimming");
        searchValues.add("running");

        String secondParam = "durationFrom";
        int durationFrom = 30;

        String thirdParam = "durationTo";
        int durationTo = 55;

        List<Activity> activities = client.search(
                param, searchValues, secondParam, durationFrom, thirdParam, durationTo
        );
        assertNotNull(activities);
    }

    @Test
    public void testDelete() {
        ActivityClient client = new ActivityClient();

        client.delete("123");
    }

    @Test
    public void testPut() {
        Activity activity = new Activity();
        activity.setId("321");
        activity.setDescription("Yoga");
        activity.setDuration(90);

        ActivityClient client = new ActivityClient();
        activity = client.update(activity);
        assertNotNull(activity);
    }

    @Test
    public void testCreate() {
        ActivityClient client = new ActivityClient();

        Activity activity = new Activity();
        activity.setDescription("Swimming");
        activity.setDuration(90);

        activity = client.create(activity);

        assertNotNull(activity);
    }

    @Test
    public void testGet() throws Exception {
        ActivityClient client = new ActivityClient();

        Activity activity = client.get("123");
        System.out.println(activity);
        assertNotNull(activity);
    }

    @Test
    public void testGetList() throws Exception {
        ActivityClient client = new ActivityClient();

        List<Activity> activities = client.get();

        assertNotNull(activities);
    }

    @Test(expected = RuntimeException.class)
    public void testGetWithBadRequest() {
        ActivityClient client = new ActivityClient();

        client.get("1");
    }

    @Test(expected = RuntimeException.class)
    public void testGetWithNotFound() {
        ActivityClient client = new ActivityClient();

        client.get("777");
    }
}