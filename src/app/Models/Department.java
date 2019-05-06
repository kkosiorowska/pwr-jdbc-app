package app.Models;

public class Department {

    private int id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

    public String getName() {
        return name;
    }

    //@XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    //@XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return getName();
    }
}