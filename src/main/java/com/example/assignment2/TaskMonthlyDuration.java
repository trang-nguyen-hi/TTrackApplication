package com.example.assignment2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;

public class TaskMonthlyDuration {
    private final SimpleStringProperty taskName;
    private final SimpleIntegerProperty month;
    private final SimpleIntegerProperty year;
    private final SimpleStringProperty totalDuration;

    TaskMonthlyDuration(String taskName, int month, int year, Duration duration) {
        this.taskName = new SimpleStringProperty(taskName);
        this.month = new SimpleIntegerProperty(month);
        this.year = new SimpleIntegerProperty(year);
        long s = duration.toSeconds();
        this.totalDuration = new SimpleStringProperty(String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60)));
    }

    public String getTaskName() {
        return taskName.get();
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public int getMonth() {
        return month.get();
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getTotalDuration() {
        return totalDuration.get();
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration.set(totalDuration);
    }
}

