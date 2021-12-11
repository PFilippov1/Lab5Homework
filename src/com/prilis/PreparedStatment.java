package com.prilis;

import java.sql.*;
import java.util.Properties;

public class PreparedStatment {
    private final static String sqlSelect = "Select id,name,speciality, salary from developers";
    private final static String sqlInsert = "INSERT INTO developers (id, name, speciality, salary) VALUES (?,?,?,?)";
    private final static String sqlUpdate = "UPDATE developers SET salary=? WHERE name=?";

    public static void main(String[] args) {
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
        try (Connection connection = DriverManager.getConnection(url, properties)) {

            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setInt(1, 5);
            statement.setString(2, "Jim");
            statement.setString(3, "C#");
            statement.setInt(4, 2500);
            int rowsUpdate = statement.executeUpdate();
            System.out.println(rowsUpdate);

            PreparedStatement statmentUpd = connection.prepareStatement(sqlUpdate);
            statmentUpd.setInt(1, 3500);
            statmentUpd.setString(2, "Marina");

            int rowsUpdate2 = statmentUpd.executeUpdate();
            System.out.println(rowsUpdate2);




            PreparedStatement statmentSelect = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statmentSelect.executeQuery(sqlSelect);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String speciality = resultSet.getString("speciality");
                int salary = resultSet.getInt("salary");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
                System.out.println("speciality: " + speciality);
                System.out.println("salary: " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
