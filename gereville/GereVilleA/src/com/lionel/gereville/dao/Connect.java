/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lionel.gereville.dao;
import java.sql.*;

/**
 *
 * @author PATRICE FRANCOIS
 */
public  class Connect {


    /**
     * 
     * @return null if any pb
     */
 public static Connection  cConnect () 
 {
      String url = "jdbc:mysql://localhost/gerville";

      try
      {
    	  Class.forName("com.mysql.jdbc.Driver").newInstance();

        //  On se connecte via la passerelle jdbc Oracle

        return  DriverManager.getConnection(url,"gervilleUser","mdp");
      }
        catch(SQLException sqlE)
        {
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
