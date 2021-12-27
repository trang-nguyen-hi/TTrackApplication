package com.example.assignment2;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Lap {
    public ObjectProperty<LocalDate> localDateProperty;
    private final SimpleIntegerProperty monthProperty;
    private final SimpleIntegerProperty yearProperty;
    private final ObjectProperty<LocalTime> startTimeProperty;
    private final ObjectProperty<LocalTime> endTimeProperty;
    private final SimpleStringProperty durationProperty;

    public SimpleStringProperty lblTime = new SimpleStringProperty("00:00:00");
    public SimpleIntegerProperty seconds = new SimpleIntegerProperty(0);
    // create a timer
    private final AnimationTimer timer = new AnimationTimer() {
        private long lastTime = 0;
        @Override
        public void handle(long now) {
            if (lastTime != 0) {
                if (now >= lastTime + 1_000_000_000) {
                    seconds.set(seconds.get() + 1);
                    lblTime.set(String.format("%02d:%02d:%02d", seconds.get() / 3600, (seconds.get() % 3600) / 60, (seconds.get() % 60)));
                    lastTime = now;
                }
            } else {
                lastTime = now;

            }
        }
        @Override
        public void stop() {
            super.stop();
            lblTime.set("00:00:00");
            lastTime = 0;
            seconds.set(0);
        }
    };

    public Lap(LocalDate localDate, LocalTime startTime, LocalTime endTime) {
        localDateProperty = new SimpleObjectProperty<>(this, "localDateProperty", localDate);
        startTimeProperty = new SimpleObjectProperty<>(this, "startTimeProperty", startTime);
        endTimeProperty = new SimpleObjectProperty<>(this, "endTimeProperty", endTime);
        long s = Duration.between(startTime, endTime).toSeconds();
        durationProperty = new SimpleStringProperty(String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60)));
        monthProperty = new SimpleIntegerProperty(localDate.getMonthValue());
        yearProperty = new SimpleIntegerProperty(localDate.getYear());
    }

    // for ongoing lap
    public Lap(LocalDate localDate, LocalTime startTime) {
        localDateProperty = new SimpleObjectProperty<>(this, "localDateProperty", localDate);
        startTimeProperty = new SimpleObjectProperty<>(this, "startTimeProperty", startTime);
        endTimeProperty = new SimpleObjectProperty<>(this, "endTimeProperty", null);
        durationProperty = new SimpleStringProperty("in progress ...");
        monthProperty = new SimpleIntegerProperty(localDate.getMonthValue());
        yearProperty = new SimpleIntegerProperty(localDate.getYear());
    }

    public void startTimer() {
        lblTime.set("00:00:00");
        timer.start();
    }

    public void stopTimer() {
        setDurationProperty(lblTime.get());
        setEndTimeProperty(LocalTime.now());
        timer.stop();
    }


    public LocalDate getLocalDateProperty() {
        return localDateProperty.get();
    }

    public void setLocalDateProperty(LocalDate localDate) {
        localDateProperty.set(localDate);
    }

    public LocalTime getStartTimeProperty() {
        return startTimeProperty.get();
    }

    public void setStartTimeProperty(LocalTime startTimeProperty) {
        this.startTimeProperty.set(startTimeProperty);
    }

    public LocalTime getEndTimeProperty() {
        return endTimeProperty.get();
    }

    public void setEndTimeProperty(LocalTime endTimeProperty) {
        this.endTimeProperty.set(endTimeProperty);
    }

    public int getMonth() {
        return monthProperty.get();
    }

    public int getYear() {
        return yearProperty.get();
    }

    public String getDurationProperty() {
        return durationProperty.get();
    }

    public void setDurationProperty(String durationProperty) {
        this.durationProperty.set(durationProperty);
    }

    @Override
    public String toString() {
        return "Lap{" +
                "localDateProperty=" + getLocalDateProperty().toString() +
                ", monthProperty=" + getMonth() +
                ", yearProperty=" + getYear() +
                ", startTimeProperty=" + getStartTimeProperty().toString() +
                ", endTimeProperty=" + getEndTimeProperty().toString() +
                ", durationProperty=" + getDurationProperty() +
                '}';
    }
}
