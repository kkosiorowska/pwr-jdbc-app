package app.controllers;

import app.DBMenage;
import app.Models.Department;
import app.Models.Product;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductsController extends BaseController{

    @FXML ListView listProducts ;
    List<Product> list = new ArrayList<>();
    ObservableList<Product> items = FXCollections.observableArrayList();
    Product selectedProduct;
    Department selectedDepartment ;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        setList();
    }

    public void setList(){
        listProducts.getItems().clear();
        list = DBMenage.getProducts();
        items.addAll(list);
        listProducts.setItems(items);

        listProducts.setCellFactory(param -> new ListCell<Product>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText("Name: " + item.getName() + " Quantity: " + item.getQuantity() + " Price: " + item.getPrice());
                }
            }
        });

        listProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
                selectedProduct= newValue;
            }
        });
    }
    public void btnBackClicked(MouseEvent mouseEvent) throws IOException {
        changeScene("MenuView",mouseEvent);
    }

    public void btnAddClicked(MouseEvent mouseEvent) {

        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Add product");
        dialog.setHeaderText("Please set input");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Label labelName = new Label("Name:");
        TextField name = new TextField("Name");
        Label labelQuantity = new Label("Quantity:");
        TextField quantity = new TextField("0");
        Label labelPrice = new Label("Price:");
        TextField price = new TextField("0.0");

        List<Department> list = DBMenage.getDepartments();
        Label labelDepartment = new Label("Department:");


        ObservableList<Department> items = FXCollections.observableArrayList();
        items.addAll(list);
        ComboBox<Department> comboBox = new ComboBox<>();
        comboBox.setItems(items);

        comboBox.setCellFactory(param -> new ListCell<Department>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });


        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                selectedDepartment= newValue;
            }
        });




        dialogPane.setContent(new VBox(5, labelName, name,labelDepartment,comboBox, labelQuantity,quantity,labelPrice, price));

        Platform.runLater(name::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Product(comboBox.getValue().getId(), name.getText(),  Integer.parseInt(quantity.getText()), Double.parseDouble(price.getText()) );

            }
            return null;
        });
        Optional<Product> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Product results) -> {

            DBMenage.addProducts(results);
        });

        setList();
    }

    public void btnUpdateClicked(MouseEvent mouseEvent) {

        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Update product");
        dialog.setHeaderText("Please set input");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Label labelName = new Label("Name:");
        TextField name = new TextField(selectedProduct.getName());
        Label labelQuantity = new Label("Quantity:");
        TextField quantity = new TextField(String.valueOf(selectedProduct.getQuantity()));
        Label labelPrice = new Label("Price:");
        TextField price = new TextField(String.valueOf(selectedProduct.getPrice()));

        List<Department> list = DBMenage.getDepartments();
        Label labelDepartment = new Label("Department:");


        ObservableList<Department> items = FXCollections.observableArrayList();
        items.addAll(list);
        ComboBox<Department> comboBox = new ComboBox<>();
        comboBox.setItems(items);
        comboBox.setCellFactory(param -> new ListCell<Department>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                selectedDepartment= newValue;
            }
        });

        dialogPane.setContent(new VBox(5, labelName, name,labelDepartment,comboBox, labelQuantity,quantity,labelPrice, price));

        Platform.runLater(name::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Product(selectedProduct.getId(),comboBox.getValue().getId(), name.getText(),  Integer.parseInt(quantity.getText()), Double.parseDouble(price.getText()) );

            }
            return null;
        });
        Optional<Product> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Product results) -> {

            DBMenage.updateProducts(results);
        });

        setList();
    }
}

