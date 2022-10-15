package com.example.supplychain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.PipedOutputStream;
import java.sql.ResultSet;

public class Product {
    // id, name, price
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleDoubleProperty price;

    public  Product(int id, String name, double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public  int getId() { return  id.get(); }
    public  String  getName() { return  name.get(); }
    public  double getPrice() { return  price.get(); }

    public static ObservableList<Product> getAllProducts(){
        DatabaseConnection dbCon = new DatabaseConnection();
        ObservableList<Product> data = FXCollections.observableArrayList();
        String selectProducts = "SELECT * FROM product";
        try{
            ResultSet rs =  dbCon.getQueryTable(selectProducts);
            while(rs.next()){
                data.add(new Product(rs.getInt("pid"), rs.getString("name"), rs.getDouble("price")));
                System.out.println(rs.getInt("pid") + " " +
                        rs.getString("name") + " " +
                        rs.getDouble("price")
                );
            }
            rs.close();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return data;
    }

    public static ObservableList<Product> getProductsByName(String productName){
        DatabaseConnection dbCon = new DatabaseConnection();
        ObservableList<Product> data = FXCollections.observableArrayList();
        String selectProducts = String.format("SELECT * FROM product WHERE name like '%%%s%%'", productName.toLowerCase());
        try{
            ResultSet rs =  dbCon.getQueryTable(selectProducts);
            while(rs.next()){
                data.add(new Product(rs.getInt("pid"), rs.getString("name"), rs.getDouble("price")));
                System.out.println(rs.getInt("pid") + " " +
                        rs.getString("name") + " " +
                        rs.getDouble("price")
                );
            }
            rs.close();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return data;
    }

}
