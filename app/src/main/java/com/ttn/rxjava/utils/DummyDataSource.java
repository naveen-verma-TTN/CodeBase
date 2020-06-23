package com.ttn.rxjava.utils;

/*
 * Created by Naveen Verma on 23/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.rxjava.model.Task;

import java.util.ArrayList;
import java.util.List;

public class DummyDataSource {
    public static List<Task> createTasksList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Take out the trash", true, 3));
        tasks.add(new Task("Walk the dog", false, 2));
        tasks.add(new Task("Make my bed", true, 1));
        tasks.add(new Task("Unload the dishwasher", false, 0));
        tasks.add(new Task("Make dinner", true, 5));
        return tasks;
    }
}
