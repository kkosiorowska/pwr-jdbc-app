package app.Models;

public class Product {

    private int id;
    private int idDep;
    private int quantity;
    private double price;
    private String name;

//    public String getNameDep() {
//        return nameDep;
//    }
//
//    public void setNameDep(String nameDep) {
//        this.nameDep = nameDep;
//    }

    //private String nameDep;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDep() {
        return idDep;
    }

    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(int id, int idDep, String name, int quantity, double price) {
        this.id = id;
        this.idDep = idDep;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Product(int idDep, String name, int quantity, double price) {
        this.id = id;
        this.idDep = idDep;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
