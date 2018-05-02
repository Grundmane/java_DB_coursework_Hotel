/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * @author Veronika
 */
public class DBclassClient {
    //Database
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true";

    //insert into db

    public Boolean add(int id, String name, String surname, int date, String country, String email, String gender, int number, String city) {

        String sql = "INSERT INTO mydbtest.clients(Id_client,Name,Surname,Date,Country,Email,Gender,PhoneNum,City) VALUES('" + id + "','" + name + "','" + surname + "','" + date + "','" + country + "','" + email + "','" + gender + "','" + number + "','" + city + "')";

        try {
            Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected to database");
            Statement s = conn.prepareStatement(sql);
            s.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //retrieve from mysql
    public DefaultTableModel getData() {
        //construct columns
        DefaultTableModel dms = new DefaultTableModel();
        dms.addColumn("Id_client");
        dms.addColumn("Name");
        dms.addColumn("Surname");
        dms.addColumn("Date");
        dms.addColumn("Country");
        dms.addColumn("Email");
        dms.addColumn("Gender");
        dms.addColumn("PhoneNum");
        dms.addColumn("City");

        //sql
        String sql = "SELECT *FROM clients";

        try {
            //get connection
            Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            // get prepared stmt
            Statement s = conn.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            //loop thru getting all names
            while (rs.next()) {
                //get cell values for each row
                String id = rs.getString(1);
                String name = rs.getString(2);
                String surname = rs.getString(4);
                int date = rs.getInt(5);
                String country = rs.getString(7);
                String email = rs.getString(3);
                String gender = rs.getString(6);
                int number = rs.getInt(8);
                String city = rs.getString(9);
                String login = rs.getString(10);
                String password = rs.getString(11);

                //add to dm rows collection
                Object[] rowData = {id, name, surname, date, country, email, gender, number, city};
                dms.addRow(rowData);
            }
            return dms;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}

