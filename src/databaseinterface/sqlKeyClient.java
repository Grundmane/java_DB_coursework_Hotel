/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Veronika
 */
public class sqlKeyClient {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true";

    public int id_incrementable() {
        Connection conn = null;

        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            ps = conn.prepareStatement("SELECT MAX(Id_client) FROM clients");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return id;
    }
}
