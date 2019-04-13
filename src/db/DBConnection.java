package db;

import java.sql.*;

public class DBConnection {

    Connection con;
    Statement stmt;

    public void connect(String dbName, String user, String pass)
    {
        //  Register the driver
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
        }
        catch (Exception E)
        {
            System.err.println("Unable to load driver.");
        }

        //  Make the connection.

        try
        {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, user, pass);
            stmt = con.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException:  " + e.getMessage());
        }
    }

    //  Close the connection....

    public void disconnect()
    {
        try
        {
            con.close();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException:  " + e.getMessage());
        }
    }
    public void insert()
    {
        try {
            String insert = "INSERT INTO simpletable VALUES (1, 'record one')";
            stmt.executeUpdate(insert);

            insert = "INSERT INTO simpletable VALUES (2, 'record two')";
            stmt.executeUpdate(insert);

            insert = "INSERT INTO simpletable VALUES (3, 'record three')";
            stmt.executeUpdate(insert);
        }
        catch (SQLException e)
        {
            System.out.println("SQLException:  " + e.getMessage());
        }
    }

    // Create the table.

    public void create()
    {

        try
        {
            String create = "create table simpletable (id int, text char(20)) ";
            stmt.executeUpdate(create);
        }
        catch (SQLException e)
        {
            System.out.println("SQLException:  " + e.getMessage());
        }
    }

    public void addNewTable(){

        try
        {
            String createString;

            createString = "create table COFFEES " +
                    "(COF_NAME VARCHAR(32), " +
                    "SUP_ID INTEGER, " +
                    "PRICE FLOAT, " +
                    "SALES INTEGER, " +
                    "TOTAL INTEGER)";

            stmt.executeUpdate(createString);

        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    // Drop the table if it already exists...

    public void drop()
    {

        try
        {
            String drop = "drop table simpletable";
            stmt.executeUpdate(drop);
        }
        catch (SQLException e)
        {
            //  Table doesn't exist...
        }
    }

    public static void main(String[] args)
    {
        DBConnection table = new DBConnection();
        table.connect("db", "root", "");

        System.out.println("after connect");

        table.drop();
        System.out.println("after drop");

        table.create();
        System.out.println("after create");

        table.addNewTable();
        table.insert();
        System.out.println("after insert");

        table.disconnect();
        System.out.println("after disconnect");
    }
}