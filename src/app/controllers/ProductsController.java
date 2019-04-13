package app.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProductsController extends BaseController{
    public void btnBackClicked(MouseEvent mouseEvent) throws IOException {
        changeScene("MenuView",mouseEvent);
    }

    public void btnAddClicked(MouseEvent mouseEvent) {
    }

    public void btnUpdateClicked(MouseEvent mouseEvent) {
    }
}
