/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseserver;

import java.sql.*; 

/**
 * Questa classe rappresenta la connessione con il database
 * @author nautilus
 */
public class DatabaseConnection {
    
    protected static Connection connection;
    protected static Statement statement;
    
    /**
     * Viene creato un oggetto dal quale si può accedere al database
     * @param username  username per accedere al database
     * @param password  password associata allo username per accedere al database
     */
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
