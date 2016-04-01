package com.jelly.repositiory;

import com.jelly.model.Activity;
import com.jelly.model.ActivitySearch;
import com.jelly.model.User;

import java.util.ArrayList;
import java.util.List;

public class ActivityRepositoryStub implements ActivityRepository {

    @Override
    public List<Activity> findAllActivities() {
        List<Activity> activities = new ArrayList<Activity>();

        Activity activity1 = new Activity();
        activity1.setDescription("Swimming");
        activity1.setDuration(55);

        activities.add(activity1);

        Activity activity2 = new Activity();
        activity2.setDescription("Cycling");
        activity2.setDuration(120);

        activities.add(activity2);

        return activities;
    }

    @Override
    public Activity findActivity(String id) {

        if (id.equals("777")) {
            return null;
        }

        Activity activity1 = new Activity();
        activity1.setId(id);
        activity1.setDescription("Swimming");
        activity1.setDuration(55);

        User user = new User();
        user.setId("1");
        user.setName("Brian");

        activity1.setUser(user);

        return activity1;
    }

    @Override
    public void create(Activity activity) {
        // insert to db
    }

    @Override
    public Activity update(Activity activity) {
        return activity;
    }

    @Override
    public void delete(String activityId) {

    }

    @Override
    public List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo) {
        List<Activity> activities = new ArrayList<Activity>();

        Activity activity = new Activity();
        activity.setId("123");
        activity.setDescription("Swimming");
        activity.setDuration(55);

        activities.add(activity);

        return activities;
    }

    @Override
    public List<Activity> findByConstraints(ActivitySearch search) {
        List<Activity> activities = new ArrayList<Activity>();

        Activity activity = new Activity();
        activity.setId("123");
        activity.setDescription("Swimming");
        activity.setDuration(55);

        activities.add(activity);

        return activities;
    }
}
