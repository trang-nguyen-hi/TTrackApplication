package com.example.assignment2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class AddNewTaskView extends BorderPane {
    Label nameLabel;
    Label detail;
    TextField nameField;
    TextField detailField;

    Button addButton;

    public AddNewTaskView() {
        this.setPadding(new Insets(10));

        // set the top label
        Label topLabel = new Label("New Task");
        topLabel.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 15));
        this.setTop(topLabel);
        setMargin(topLabel, new Insets(10));

        // set up the textfields
        nameLabel = new Label("Name:");
        nameField = new TextField();
        nameField.setPromptText("Enter task name here");
        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(nameLabel, nameField);

        detail = new Label("Detail:");
        detailField = new TextField();
        detailField.setPromptText("Enter task detail/note here");
        HBox detailBox = new HBox();
        detailBox.getChildren().addAll(detail, detailField);

        VBox info = new VBox(nameBox, detailBox);
        info.setSpacing(10);
        info.setAlignment(Pos.CENTER);
        this.setCenter(info);
        setAlignment(info, Pos.CENTER);
        setMargin(info, new Insets(10));

        addButton = new Button("Add");
        VBox addBox = new VBox(addButton);
        addBox.setAlignment(Pos.CENTER_RIGHT);
        this.setBottom(addBox);
        setMargin(addBox, new Insets(10));
    }

    public void setController(Controller newController) {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            newController.handleAddNewTask(nameField.getText(), detailField.getText());
            nameField.setText("");
            detailField.setText("");
        });
    }

}
