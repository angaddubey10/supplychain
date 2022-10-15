package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {

    public Pane getAllProducts(){
        TableView<Product> table = new TableView<>();

        //Columns
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory( new PropertyValueFactory<>("id"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory( new PropertyValueFactory<>("name"));
        TableColumn priceCol = new TableColumn("Price");
        priceCol.setCellValueFactory( new PropertyValueFactory<>("price"));

        ObservableList<Product> data = FXCollections.observableArrayList(
                new Product(2, "abc", 3456.0),
                new Product(2, "pqr", 374.67)
        );

        ObservableList<Product> productData = Product.getAllProducts();

        table.setItems(productData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(idCol, nameCol, priceCol);

        Pane tpane = new Pane();
        tpane.getChildren().add(table);
        tpane.setTranslateY(100);
        return tpane;
    }

}
