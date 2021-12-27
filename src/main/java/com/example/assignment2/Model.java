package com.example.assignment2;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Model {
    ArrayList<Task> tasks;
    ArrayList<ModelSubscriber> subs;
    Lap currentLap;
    private final ObservableList<TaskMonthlyDuration> monthlyDurations;

    public Model() {
        tasks = new ArrayList<>();
        subs = new ArrayList<>();
        monthlyDurations = FXCollections.observableArrayList();
    }

    // get the monthly duration list
    public ObservableList<TaskMonthlyDuration> getMonthlyDurations() {
        return monthlyDurations;
    }

    // add new task to list
    public void addNewTask(String taskName, String taskDetail) {
        tasks.add(new Task(taskName, taskDetail));
        notifySubsTaskAdded();
    }

    // start a task
    public void startTask(Task task) {
        // if this task is already started, do nothing
        if (task.getLastLap() != null && task.getLastLap().getEndTimeProperty() == null) {
            return;
        } else {
            currentLap = new Lap(LocalDate.now(), LocalTime.now());
            task.addLap(currentLap);
            currentLap.startTimer();
            currentLap.seconds.addListener((observableValue, number, t1) -> notifyTimerChanged());
        }
    }

    // stop a task
    public void stopTask(Task task) {
        // if this task is already ended, do nothing
        if (task.getLastLap() != null && task.getLastLap().getEndTimeProperty() != null) {
            return;
        } else {
            task.getLastLap().stopTimer();
            addLapToMonthlyDuration(task.getLastLap(), task);
            notifyTimerChanged();
            notifyTaskHistoryChanged();
        }
    }

    // parse a string of form hh:mm:ss to duration type
    private Duration parseDurationStr(String normalForm) {
        System.out.println(normalForm);
        String string = "PT" + Integer.valueOf(normalForm.substring(0, 2)) + "H"
                + Integer.valueOf(normalForm.substring(3, 5)) + "M"
                + Integer.valueOf(normalForm.substring(6, 8)) + "S";
        System.out.println(string);
        return Duration.parse(string);
    }

    // add a lap to monthly duration list
    private void addLapToMonthlyDuration(Lap lap, Task task) {
        // if the list is empty, just add it
        if (monthlyDurations.isEmpty()) {
            monthlyDurations.add(new TaskMonthlyDuration(task.getTaskName(), lap.getMonth(), lap.getYear(), parseDurationStr(lap.getDurationProperty())));
        } else {
            Boolean found = false;
            // go through the list, check if there's any entry that has the same name, month and year
            for (TaskMonthlyDuration single : monthlyDurations) {
                // if found, add the lap duration to the entry duration
                if (single.getTaskName().compareTo(task.getTaskName()) == 0 && single.getMonth() == lap.getMonth() && single.getYear() == lap.getYear() && lap.getEndTimeProperty() != null) {
                    found = true;
                    Duration newTotal = parseDurationStr(single.getTotalDuration()).plus(parseDurationStr(lap.getDurationProperty()));
                    long seconds = newTotal.toSeconds();
                    String totalString = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
                    single.setTotalDuration(totalString);
                }
            }
            // if not found, just add to the list
            if (!found) {
                monthlyDurations.add(new TaskMonthlyDuration(task.getTaskName(), lap.getMonth(), lap.getYear(), parseDurationStr(lap.getDurationProperty())));
            }
        }
    }

    public Task getLastTask() {
        return getTasks().get(getTasks().size() - 1);
    }

    public void addSubscriber(ModelSubscriber newSub) {
        subs.add(newSub);
    }

    private void notifySubsTaskAdded() {
        subs.forEach(ModelSubscriber::modelTaskAdded);
    }

    private void notifyTimerChanged() {
        subs.forEach(ModelSubscriber::modelTimerChanged);
    }

    private void notifyTaskHistoryChanged() {
        subs.forEach(ModelSubscriber::modelTaskHistoryChanged);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
