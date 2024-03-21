/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author trank
 */

public class DBConnection {
	
	private final String serverName = "localhost";
	private final String dbName = "Mini_Soccer_field_db";
	private final String portNumber = "3306";
        private final String user = "root";
        private final String password = "";
	
	public Connection getConnection() throws Exception {
            String url = "jdbc:mysql://" + serverName + ":" + portNumber +"/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        }
        public static void main(String[] args) {

		try {

		System.out.println(new DBConnection().getConnection());

		} catch (Exception e) {

			e.printStackTrace();

		}
        }
}