package app.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentController extends BaseController {

    public void initialize(URL url, ResourceBundle resourceBundle) {

        getDepartments();

    }

    public void getDepartments () {

//        SessionFactory factory = changeConfiguration(user.getLogin(), user.getPassword() );
//        Session session = factory.getCurrentSession();
//
//        try {
//            session.getTransaction().begin();
//            String hql =    "select n " +
//                    "from BankingBillEntity b " +
//                    "INNER JOIN BillNumbersEntity n ON n.id = b.billNumberId ";
//            try {
//                Query<BillNumbersEntity> query = session.createQuery(hql);
//                List<BillNumbersEntity> data = query.getResultList();
//                items.addAll(data);
//                list.setItems(items);
//
//            }
//            catch (NoResultException ex)
//            {
//                ShowError("Wystąpił błąd!");
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        }

    }

    public void btnBackClicked(MouseEvent mouseEvent) throws IOException {
        changeScene("MenuView",mouseEvent);
    }

    public void btnAddClicked(MouseEvent mouseEvent) {
    }

    public void btnUpdateClicked(MouseEvent mouseEvent) {
    }
}
