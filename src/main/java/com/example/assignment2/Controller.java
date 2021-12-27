package com.example.assignment2;

public class Controller {
    Model model;

    public Controller() {
    }

    public void setModel(Model newModel) {
        model = newModel;
    }

    //handle adding new task
    public void handleAddNewTask(String taskName, String taskDetail) {
        model.addNewTask(taskName, taskDetail);
    }

    //handle starting a task
    public void startTask(Task task) {
        model.startTask(task);
    }

    //handle stopping a task
    public void stopTask(Task task) {
        model.stopTask(task);
    }
}

