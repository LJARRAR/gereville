package com.lionel.gereville.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.lionel.gereville.model.Ville;

public class VilleDAO {
	
	private static Connection c;
	 
	 
	 public static List<Ville> getVille(){
		 
		 c = Connect.cConnect();
		 
		 
		 List<Ville> ps = new ArrayList<>();
       // test avec select
       Statement stm;
		try {
			stm = c.createStatement();
			
			String sql = "select * from ville";
	        ResultSet rs = stm.executeQuery(sql);
	      
	        
	        while (rs.next()){
	        	
	        	String nomVille = rs.getString("nom");
	        	int nbhabitant = rs.getInt("nbhabitant"); 
	        
	        	Ville v = new Ville();
	        	v.setNumVille(rs.getInt("num"));
	        	v.setNom(nomVille);
	        	v.setNbHabitants(nbhabitant);
	        	
	        	ps.add(v);
	        }
	        rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ps;
      
		 
	 }
	 
	 public static List<Ville> getVilles(int numVille){
		 
		 
		 
		 String sql = "SELECT num, nom, nbhabitants FROM ville " +
				 " INNER JOIN ville_pays ON (ville_pays.num_ville = ville.num )" +
				"  WHERE ville_pays.num_pays =" + numVille;
		 
		 c = Connect.cConnect();
		 
		 List<Ville> villes = new ArrayList<>();
		   Statement stm;
			try {
				stm = c.createStatement();
				
		        ResultSet rs = stm.executeQuery(sql);
		      
		        
		        while (rs.next()){
		        	int nbhabitant = rs.getInt("nbhabitants");
		        	String nomVille = rs.getString("nom");
		        	numVille = rs.getInt(numVille);
		        	
		        	Ville v = new Ville();
		        	v.setNom(nomVille);
		        	v.setNbHabitants(nbhabitant);
		        	v.setNumVille(numVille);
		        	
		        	
		        	villes.add(v);
		        }
		        rs.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return villes;
	 }
	 
	 public static void createVille(Ville v) {
		 
		 c = Connect.cConnect();
		 PreparedStatement stm;
		try {
			stm = c.prepareStatement("INSERT INTO ville (nom, nbhabitants) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, v.getNom());
			stm.setDouble(2, v.getNbHabitants());
			
			stm.executeUpdate ();
			 ResultSet rs = stm.getGeneratedKeys();
			 if (rs.next()) {
		          int villenum = rs.getInt(1);
		          stm = c.prepareStatement("insert into ville_pays (num_pays, num_ville) values (?,?)");
		          stm.setInt(1, v.getPays().getNum());
		          stm.setInt(2, villenum);
		          stm.executeUpdate();
		      }
		   
		   stm.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		 
	 }

public static void deleteVille(Ville v) {
		
		Connection c = Connect.cConnect();
		 PreparedStatement stm;
		try {				
			c.setAutoCommit(false);	 	
			stm = c.prepareStatement("DELETE from ville_pays where num_ville ="+v.getNumVille());
			stm.executeUpdate ();
			stm = c.prepareStatement("DELETE from ville where num =" +v.getNumVille());  
			stm.executeUpdate ();
			
			c.commit();
			c.setAutoCommit(true);
			 stm.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	
}
