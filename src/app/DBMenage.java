package app;

import app.Models.Department;
import app.Models.Product;
import org.hibernate.query.Query;

import java.util.List;

public class DBMenage {

    public static List<Department> getDepartments(){

        DBConnection.connect();

        String hql = "select d from Department d";
//        String hql = "SELECT p.idProducts, p.idDepertments, p.name, p.quantity, p.price, d.nameDep as name \n" +
//                     "FROM Product as p \n" +
//                     "INNER JOIN Department as d\n" +
//                     "on d.idDepartments = p.idDepertments";

        Query<Department> query = (Query<Department>) DBConnection.executeQuery(hql);
        List<Department> lista = query.getResultList();

        DBConnection.disconnect();

        return lista;
    }

    public static List<Product> getProducts(){

        DBConnection.connect();

        String hql = "select p from Product p";
        Query<Product> query = (Query<Product>) DBConnection.executeQuery(hql);
        List<Product> lista = query.getResultList();

        DBConnection.disconnect();

        return lista;
    }

    public static String getDepartmentName(int id){

        DBConnection.connect();

        String hql = "select name from Department where id="+id;;
        Query query =  DBConnection.executeQuery(hql);
        String name = (String) query.getSingleResult();

        DBConnection.disconnect();

        return name;
    }

    public static void addDepartments(Department newDep){

        DBConnection.connect();

        String hql = "insert into departments ( name ) values(\""+newDep.getName()+"\")";
        DBConnection.executeUpdate(hql);

        DBConnection.disconnect();
    }

    public static void addProducts(Product newPro){

        DBConnection.connect();

        String hql = "insert into products ( idDepertments, name, quantity, price ) values("+newPro.getIdDep()+",\""+newPro.getName()+"\","+newPro.getQuantity()+","+newPro.getPrice()+")";
        DBConnection.executeUpdate(hql);

        DBConnection.disconnect();
    }


    public static void updateDepartments(Department Dep){

        DBConnection.connect();
        String hql = "update departments set name = '" + Dep.getName() + "' where idDepartments = " + Dep.getId() ;
        //String hql = "update Department set name = '" + Dep.getName() + "' where id = " + Dep.getId() ;
        DBConnection.executeUpdate(hql);

        DBConnection.disconnect();
    }

    public static void updateProducts(Product Pro){

        DBConnection.connect();
        String hql = "update products set name = '" + Pro.getName() + "' , quantity ="+Pro.getQuantity()+", price="+Pro.getPrice()+"  where idProducts = " + Pro.getId() ;
        //String hql = "update Department set name = '" + Dep.getName() + "' where id = " + Dep.getId() ;
        DBConnection.executeUpdate(hql);

        DBConnection.disconnect();
    }
}
