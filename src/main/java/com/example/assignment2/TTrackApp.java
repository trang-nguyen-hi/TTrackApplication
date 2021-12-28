package com.example.assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TTrackApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        MainView mainView = new MainView();

        // set up MVC architecture
        Model model = new Model();
        Controller controller = new Controller();
        mainView.setModel(model);
        mainView.setController(controller);
        controller.setModel(model);
        model.addSubscriber(mainView);

        primaryStage.setScene(new Scene(mainView));
        primaryStage.setTitle("TTrack");

        primaryStage.show();
    }
}