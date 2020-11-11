package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DemoApplication {

	static final String DB_URL = "jdbc:mysql://Full2020-86383:3306/demo?useSSL=false&serverTimezone=UTC";
//	static final String DB_URL = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC";
//	static final String DB_URL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";

	static final String USER = "ppawlak";
	static final String PASS = "ppawlak";
	static final String DB_NAME = "demo";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			createDataBaseIfNotExists(connection,DB_NAME);
			statement = connection.createStatement();

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(statement!=null)
					statement.close();
			}catch(SQLException se2){
			}
			try{
				if(connection!=null)
					connection.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		SpringApplication.run(DemoApplication.class, args);
	}

	public static void createDataBaseIfNotExists(Connection connection, String dbName) throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("CREATE DATABASE IF NOT EXISTS " + dbName +";");
		statement.close();
		Statement statement2 = connection.createStatement();
		statement2.execute("CREATE TABLE IF NOT EXISTS demo.users (`id` int(11) NOT NULL AUTO_INCREMENT, `login` varchar(60) DEFAULT NULL, `password` varchar(60) DEFAULT NULL, PRIMARY KEY (`id`))");
		statement2.close();
	}
}
