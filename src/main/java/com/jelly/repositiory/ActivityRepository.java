package com.jelly.repositiory;

import com.jelly.model.Activity;
import com.jelly.model.ActivitySearch;

import java.util.List;

public interface ActivityRepository {
    List<Activity> findAllActivities();

    Activity findActivity(String activityId);

    void create(Activity activity);

    Activity update(Activity activity);

    void delete(String activityId);

    List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo);

    List<Activity> findByConstraints(ActivitySearch search);
}
