package com.prilis;

import java.sql.*;
import java.util.Properties;

public class Statement {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/pr";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        try (Connection connection = DriverManager.getConnection(url, prop);
             java.sql.Statement statement = connection.createStatement()) {
            String sql = "Select id, name, speciality, salary from developers";
            Boolean isRetrived = statement.execute(sql); //показывает доступен ли результат запроса
            System.out.println("Is data retrived: " + isRetrived);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String speciality = resultSet.getString("speciality");
                int salary = resultSet.getInt("salary");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
                System.out.println("speciality: " + speciality);
                System.out.println("salary: " + salary);
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
