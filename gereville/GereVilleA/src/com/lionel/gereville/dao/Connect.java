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
      String url = "jdbc:mysql://localhost/gereville";

      try
      {
    	  Class.forName("com.mysql.jdbc.Driver").newInstance();

        //  On se connecte via la passerelle jdbc Oracle

        return  DriverManager.getConnection(url,"gereville_user","devsujetado");
      }
        catch(SQLException sqlE)
        {
            System.out.println("Sql Erreur " + sqlE.getMessage());
            return null;
        }
        catch(Exception e)
        {
           e.printStackTrace();
            return null;
        }
 }
}
