/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lionel.gereville.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PATRICE FRANCOIS
 */
public  class Connect {


    /**
     * 
     * @return RunTimeException() if any pb
     */
 public static Connection  cConnect () 
 {
      final String URL = "jdbc:mysql://localhost/gereville";

      try
      {
    	  Class.forName("com.mysql.jdbc.Driver").newInstance();

        // 
        return  DriverManager.getConnection(URL,"gereville_user","gereville");
      }
        catch(SQLException sqlE)
        {
        	//TODO Logging
            System.out.println("Sql Erreur " + sqlE.getMessage());
            throw new RuntimeException();
        }
        catch(Exception e)
        {
           e.printStackTrace();
           throw new RuntimeException();
        }
 }
}
