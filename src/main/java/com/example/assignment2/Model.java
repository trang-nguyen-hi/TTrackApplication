package com.example.assignment2;


import java.util.ArrayList;

public class Model {
    ArrayList<Task> tasks;
    ArrayList<ModelSubscriber> subs;

    public Model() {
        tasks = new ArrayList<>();
        subs = new ArrayList<>();
    }

    // add new task to list
    public void addNewTask(String taskName, String taskDetail) {
        if ( getTasks().stream().noneMatch(s -> s.getTaskName().equals(taskName))) {
            Task task = new Task(taskName, taskDetail);
            tasks.add(task);
            notifySubsTaskAdded(task);
        }
        else{
            notifySubsTaskAdded(null);
        }
    }

    // start a task
    public void startTask(Task task) {
        task.startLap();
    }

    // stop a task
    public void stopTask(Task task) {
        // if this task is already ended, do nothing
        task.stopLap();
        notifyTaskHistoryChanged();
    }

    public void addSubscriber(ModelSubscriber newSub) {
        subs.add(newSub);
    }

    private void notifySubsTaskAdded(Task task) {
        subs.forEach(sub -> sub.modelTaskAdded(task));
    }

    private void notifyTaskHistoryChanged() {
        subs.forEach(ModelSubscriber::modelTaskHistoryChanged);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
