/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*; 

/**
 *
 * @author nautilus
 */
public class DatabaseConnection {
    
    protected static Connection connection;
    protected static Statement statement;
    
    public DatabaseConnection(String username, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProgettoIngSW?autoReconnect=true&useSSL=false",username,password);
            this.statement = connection.createStatement();
            
        }catch(Exception ex){
            System.out.println("DatabaseConnection ERROR:" + ex);
        }
    }
}
