package app.controllers;

import app.DBMenage;
import app.Models.Department;
import app.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import org.hibernate.query.Query;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DepartmentController extends BaseController {

    @FXML ListView listDepartments ;
    List<Department> list = new ArrayList<>();
    ObservableList<Department> items = FXCollections.observableArrayList();
    Department selectedDepartment;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        setList();
    }

    public void setList(){
        listDepartments.getItems().clear();
        list = DBMenage.getDepartments();
        items.addAll(list);
        listDepartments.setItems(items);

        listDepartments.setCellFactory(param -> new ListCell<Department>() {
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

        listDepartments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                selectedDepartment= newValue;
            }
        });
    }



    public void btnBackClicked(MouseEvent mouseEvent) throws IOException {
        changeScene("MenuView",mouseEvent);
    }

    @FXML public void btnAddClicked(MouseEvent mouseEvent) {
        TextInputDialog dialog = new TextInputDialog("name");
        dialog.setTitle("Add new department");
        dialog.setHeaderText("Set input");
        dialog.setContentText("Name of new department: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            Department newDep = new Department(name);
            DBMenage.addDepartments(newDep);
        });
        setList();
    }

    public void btnUpdateClicked(MouseEvent mouseEvent) {
        TextInputDialog dialog = new TextInputDialog(selectedDepartment.getName());
        dialog.setTitle("Update department");
        dialog.setHeaderText("Set input");
        dialog.setContentText("Name of department: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            selectedDepartment.setName(name);
            DBMenage.updateDepartments(selectedDepartment);
        });
        setList();
    }
}
