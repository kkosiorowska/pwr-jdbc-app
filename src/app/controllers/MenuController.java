package app.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuController extends BaseController{
    public void btnProductsClicked(MouseEvent mouseEvent) throws IOException {
        changeScene("ProductsView",mouseEvent);
    }

    public void btnDepartmentsClicked(MouseEvent mouseEvent) throws IOException {
        changeScene("DepartmentView",mouseEvent);
    }
}
