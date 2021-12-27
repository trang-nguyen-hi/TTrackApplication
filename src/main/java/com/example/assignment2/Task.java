package com.example.assignment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;


public class Task {
    private final SimpleStringProperty taskNameProperty;
    private final SimpleStringProperty taskDetailProperty;
    private final ObservableList<Lap> lapList;

    public Task(String taskName, String taskDetail) {
        taskNameProperty = new SimpleStringProperty(taskName);
        taskDetailProperty = new SimpleStringProperty(taskDetail);
        lapList = FXCollections.observableArrayList();
    }

    public String getTaskName() {
        return taskNameProperty.get();
    }

    // get the newest lap of this task
    public Lap getLastLap() {
        if (lapList.isEmpty()) {
            return null;
        } else {
            return lapList.get(lapList.size() - 1);
        }
    }

    public String getDetail() {
        return taskDetailProperty.get();
    }

    public void addLap(Lap lap) {
        lapList.add(lap);
    }

    public ObservableList<Lap> getLapList() {
        return lapList;
    }

    @Override
    public String toString() {
        return lapList.get(0).toString();
    }
}
