package com.example.assignment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private final SimpleStringProperty taskNameProperty;
    private final SimpleStringProperty taskDetailProperty;
    private final ObservableList<Lap> lapList;
    protected final ObservableList<TaskMonthlyDuration> monthlyDurations;

    protected TaskView taskView;
    protected Lap currentLap;

    public Task(String taskName, String taskDetail) {
        taskNameProperty = new SimpleStringProperty(taskName);
        taskDetailProperty = new SimpleStringProperty(taskDetail);
        lapList = FXCollections.observableArrayList();
        monthlyDurations = FXCollections.observableArrayList();
    }

    public void setTaskView(TaskView taskView) {
        this.taskView = taskView;
    }

    public String getTaskName() {
        return taskNameProperty.get();
    }

    // start a task
    public void startLap() {
        if (currentLap == null) {
            currentLap = new Lap(LocalDate.now(), LocalTime.now());
            addLap(currentLap);
            currentLap.startTimer();
            currentLap.seconds.addListener((observableValue, number, t1) -> notifyTimerChanged());
        }
    }

    // stop a task
    public void stopLap() {
        // if this task is already ended, do nothing
        if (currentLap != null && currentLap.getEndTimeProperty() == null) {
            currentLap.stopTimer();
            notifyTimerChanged();
            notifyLapTableChanged();
            addLapToMonthlyDuration();
            currentLap = null;
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
    private void addLapToMonthlyDuration() {
        // if the list is empty, just add it
        if (monthlyDurations.isEmpty()) {
            monthlyDurations.add(new TaskMonthlyDuration(this.getTaskName(),
                    currentLap.getMonth(),
                    currentLap.getYear(),
                    parseDurationStr(currentLap.getDurationProperty())));
        } else {
            Boolean found = false;
            // go through the list, check if there's any entry that has the same month and year
            for (TaskMonthlyDuration single : monthlyDurations) {
                // if found, add the lap duration to the entry duration
                if ( single.getMonth() == currentLap.getMonth() && single.getYear() == currentLap.getYear() && currentLap.getEndTimeProperty() != null) {
                    found = true;
                    Duration newTotal = parseDurationStr(single.getTotalDuration()).plus(parseDurationStr(currentLap.getDurationProperty()));
                    long seconds = newTotal.toSeconds();
                    String totalString = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
                    single.setTotalDuration(totalString);
                }
            }
            // if not found, just add to the list
            if (!found) {
                monthlyDurations.add(new TaskMonthlyDuration(this.getTaskName(),
                        currentLap.getMonth(),
                        currentLap.getYear(),
                        parseDurationStr(currentLap.getDurationProperty())));
            }
        }
    }

    private void notifyLapTableChanged() {
        taskView.updateTable();
    }

    private void notifyTimerChanged() {
        taskView.updateTimer();
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
