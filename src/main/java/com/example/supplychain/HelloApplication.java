package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    Button loginButton;

    private static final int width = 700, height = 600, upperLine = 50;

    private Pane headerBar(){
        Pane topPane = new Pane();
        topPane.setPrefSize(width, upperLine-10);

        TextField searchText = new TextField();
        searchText.setPromptText("Please search here");
        searchText.setTranslateX(100);
        int searchEnd = 400;
        Button searchButton = new Button("Search");
        searchButton.setTranslateX(searchEnd);

        Button loginButton = new Button("Login");
        loginButton.setTranslateX(searchEnd+80);

        topPane.getChildren().addAll(searchText, searchButton, loginButton);
        topPane.setTranslateY(10);

        return topPane;
    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height+upperLine);

        root.getChildren().add(headerBar());
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}