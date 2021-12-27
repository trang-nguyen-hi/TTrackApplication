package com.example.assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TTrackApp extends Application {
    private Model model;
    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        MainView mainView = new MainView();

        // set up MVC architecture
        model = new Model();
        controller = new Controller();
        mainView.setModel(model);
        mainView.setController(controller);
        controller.setModel(model);
        model.addSubscriber(mainView);

        Stage stage = primaryStage;
        stage.setScene(new Scene(mainView));
        stage.setTitle("TTrack");

        stage.show();
    }
}