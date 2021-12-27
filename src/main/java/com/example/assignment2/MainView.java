package com.example.assignment2;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainView extends VBox implements ModelSubscriber {
    protected HomeView homeView;
    protected HistoryView historyView;
    protected Model model;

    public MainView() {
        TabPane tabPane = new TabPane();

        // add Home tab
        homeView = new HomeView();
        Tab tab1 = new Tab("Home", homeView);

        // add History tab
        historyView = new HistoryView();
        Tab tab2 = new Tab("History", historyView);

        tab1.setClosable(false);
        tab2.setClosable(false);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);

        this.getChildren().addAll(tabPane);
        setVgrow(tabPane, Priority.ALWAYS);
    }

    // set model and add the children view as modelSubscribers
    public void setModel(Model newModel) {
        model = newModel;
        homeView.setModel(model);
        historyView.setModel(model);
        model.addSubscriber(homeView);
        model.addSubscriber(historyView);
    }

    // set controller for the children view
    public void setController(Controller newController) {
        homeView.setController(newController);
        historyView.setController(newController);
    }

    @Override
    public void modelTaskAdded() {
    }

    @Override
    public void modelTimerChanged() {
    }

    @Override
    public void modelTaskHistoryChanged() {
    }

}
