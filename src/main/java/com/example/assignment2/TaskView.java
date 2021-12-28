package com.example.assignment2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskView extends VBox{
    Button startButton;
    Button stopButton;
    Label counter;
    Label detail;
    Task task;

    BorderPane timerView;
    TableView tableView;

    public TaskView(Task task){
        this.task = task;
        timerView = timerView();
        tableView = taskHistory();

        this.getChildren().addAll(timerView, tableView);
        this.setVgrow(timerView, Priority.ALWAYS);
    }

    public Task getTask() {
        return task;
    }

    public void setDetail(String text){ detail.setText("Detail: " + text);}

    public void setLabel(String text){
        counter.setText(text);
    }

    private BorderPane timerView(){
        HBox timerView = new HBox();

        // set up the start button
        startButton = new Button("Start");
        VBox startBox = new VBox(startButton);
        startBox.setAlignment(Pos.CENTER);

        // set up the timer label
        counter = new Label("00:00:00");
        counter.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 35));
        VBox counterBox = new VBox(counter);
        counterBox.setPrefHeight(150);
        counterBox.setAlignment(Pos.CENTER);

        // set up the stop button
        stopButton = new Button("Stop");
        VBox stopBox = new VBox(stopButton);
        stopBox.setAlignment(Pos.CENTER);

        // make the box resizing based on the window size
        timerView.getChildren().addAll(startBox, counterBox, stopBox);
        timerView.setHgrow(startBox, Priority.ALWAYS);
        timerView.setHgrow(counterBox, Priority.ALWAYS);
        timerView.setHgrow(stopBox, Priority.ALWAYS);

        // add the at the bottom left
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(timerView);
        detail = new Label();
        borderPane.setBottom(detail);

        return borderPane;
    }

    private TableView taskHistory(){
        TableView table = new TableView();
        table.setEditable(false);

        TableColumn<Lap, LocalDate> dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("localDateProperty"));

        TableColumn startTimeCol = new TableColumn("Start time");
        startTimeCol.setCellValueFactory(new PropertyValueFactory<Lap, LocalTime>("startTimeProperty"));

        TableColumn endTimeCol = new TableColumn("End time");
        endTimeCol.setCellValueFactory(new PropertyValueFactory<Lap, LocalTime>("endTimeProperty"));

        TableColumn durationCol = new TableColumn("Duration");
        durationCol.setCellValueFactory(new PropertyValueFactory<Lap, String>("durationProperty"));

        dateCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        startTimeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        endTimeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        durationCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        table.setMaxHeight(200);

        table.getColumns().addAll(dateCol, startTimeCol, endTimeCol, durationCol);
        return table;
    }

    // update the timer of this view
    public void updateTimer(){
        if (task.currentLap != null){
            setLabel(getTask().currentLap.lblTime.get());
        }
    }

    // update the table of this view
    public void updateTable(){
        tableView.refresh();
        tableView.setItems(getTask().getLapList());
    }

    public void setController(Controller newController) {
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> newController.startTask(this.task));
        stopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> newController.stopTask(this.task));
    }
}
