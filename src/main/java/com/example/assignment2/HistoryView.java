package com.example.assignment2;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoryView extends TableView implements ModelSubscriber {
    protected Model model;

    public HistoryView() {
        this.setEditable(false);

        TableColumn taskCol = new TableColumn("Task Name");
        taskCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, String>("taskName"));

        TableColumn monthCol = new TableColumn("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, Integer>("month"));

        TableColumn yearCol = new TableColumn("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, Integer>("year"));

        TableColumn totalCol = new TableColumn("Total Time");
        totalCol.setCellValueFactory(new PropertyValueFactory<TaskMonthlyDuration, String>("totalDuration"));

        this.getColumns().addAll(taskCol, monthCol, yearCol, totalCol);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setController(Controller newController) {
    }

    //update the history table
    public void updateHistory() {
        this.refresh();
        this.setItems(model.getMonthlyDurations());
    }

    @Override
    public void modelTaskAdded() {

    }

    @Override
    public void modelTimerChanged() {
    }

    @Override
    public void modelTaskHistoryChanged() {
        updateHistory();
    }
}
