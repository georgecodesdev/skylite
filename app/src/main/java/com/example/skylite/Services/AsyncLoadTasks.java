package com.example.skylite.Services;

import com.example.skylite.Activities.ActivityCalendar;
import com.google.api.services.tasks.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Asynchronously load the tasks.
 *
 * @author Yaniv Inbar
 */
public class AsyncLoadTasks extends CommonAsyncTask {

    AsyncLoadTasks(ActivityCalendar tasksSample) {
        super(tasksSample);
    }

    @Override
    protected void doInBackground() throws IOException {
        List<String> result = new ArrayList<String>();
        List<Task> tasks =
                client.tasks().list("@default").setFields("items/title").execute().getItems();
        if (tasks != null) {
            for (Task task : tasks) {
                result.add(task.getTitle());
            }
        } else {
            result.add("No tasks.");
        }
        activity.tasksList = result;
    }

    public static void run(ActivityCalendar tasksSample) {
        new AsyncLoadTasks(tasksSample).execute();
    }
}
