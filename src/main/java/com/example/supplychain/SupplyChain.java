package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {

    Button loginButton;

    Pane bodyPane;

    public static final int width = 700, height = 600, upperLine = 50;

    ProductDetails productDetails = new ProductDetails();

    private Pane headerBar(){
        Pane topPane = new Pane();
        topPane.setPrefSize(width, upperLine-10);

        TextField searchText = new TextField();
        searchText.setPromptText("Please search here");
        searchText.setTranslateX(100);
        int searchEnd = 400;
        Button searchButton = new Button("Search");
        searchButton.setTranslateX(searchEnd);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String search = searchText.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(search));
            }
        });

        loginButton = new Button("Login");
        loginButton.setTranslateX(searchEnd+80);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });


        topPane.getChildren().addAll(searchText, searchButton, loginButton);
        topPane.setTranslateY(10);

        return topPane;
    }

    private GridPane loginPage(){
        Label emilLabel = new Label("E-mail");
        Label passwordLabel = new Label("Password");
        TextField emailText = new TextField();
        emailText.setPromptText("Please enter email");
        PasswordField passwordText = new PasswordField();
        passwordText.setPromptText("Please enter password");

        Button localLoginButton = new Button("Login");
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                emailText.setText("");
                passwordText.setText("");
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.setMinSize(bodyPane.getWidth(), bodyPane.getHeight());

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(emilLabel, 0,0);
        gridPane.add(emailText,1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordText, 1, 1);
        gridPane.add(localLoginButton, 0, 2);
        gridPane.add(clearButton, 1, 2);


        return gridPane;
    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height+upperLine);

        bodyPane = new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(upperLine);

        bodyPane.getChildren().add(productDetails.getAllProducts());

        root.getChildren().addAll(headerBar(), bodyPane);
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}