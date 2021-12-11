package com.prilis;

import java.sql.*;
import java.util.Properties;

public class PreparedStatSavePoints {
    //private final static String SQLSELECT = "select * from developers";
    private final static String SQLINSERT = "INSERT INTO developers (id, name, speciality, salary) VALUES (?,?,?,?)";
    private final static String SQLUPDATE = "UPDATE developers SET salary=? WHERE name=?";

    public static void main(String[] args) throws SQLException {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка драйвера");
        }

        String url = "jdbc:mysql://localhost:3306/pr";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "root");

        Connection connection = DriverManager.getConnection(url, properties);
        connection.setAutoCommit(false); // false, now we can do rollback and do commit manually
        Savepoint savepoint1 = connection.setSavepoint("Savepoint1"); // now  we use savepoint
        try {

            PreparedStatement statement1 = connection.prepareStatement(SQLUPDATE);
            statement1.setInt(1, 3600);
            statement1.setString(2, "Marina");
            statement1.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(SQLINSERT);
            statement.setInt(1, 4);
            statement.setString(2, "Leya");
            statement.setString(3, "HR");
            statement.setInt(4, 1500);
            statement.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(savepoint1);
        }
        connection.close();

    }
}

