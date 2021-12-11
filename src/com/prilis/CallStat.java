package com.prilis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CallStat {
    private final static String SQLName = "{call show_name (?,?)}";
    private final static String SQLSal = "{call person_salary (?,?)}";
    private final static String SQLSelect = "Select id,name,speciality, salary from developers";

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

            CallableStatement statement = connection.prepareCall(SQLSal);
            // по введенной зп получим имя разработчика , но не работает, если больше одного((
            int salary_dev = 3500;
            statement.setInt(1, salary_dev);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            String person_name = statement.getString(2);
            System.out.println("Developer with salary: " + salary_dev + "    "+
                    "It is a : " +  person_name);





            CallableStatement statement2 = connection.prepareCall(SQLName);
            // по ID получаем имя разработчика
            int dev_id=3;
            statement2.setInt(1, dev_id);
            statement2.registerOutParameter(2, Types.VARCHAR);
            statement2.execute();

            String person_name2 = statement2.getString(2);
            System.out.println("ID developer is: " + dev_id +
                    "      " + "Name is : "+ person_name2);
            System.out.println("--------------------------------------");






            ResultSet resultSet = statement2.executeQuery(SQLSelect);
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

//            List<String> sal = new ArrayList<>();
//            resultSet = statement2.executeQuery(SQLSal);
//            while (resultSet.next()) {
//                sal.add(person_name);
//            }
//            System.out.println(person_name);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
