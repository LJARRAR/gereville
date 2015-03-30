package com.lionel.gereville.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;

public class PaysDAO {
	 
	 
	 public static List<Pays> getPays(){
		 
		 Connection c = Connect.cConnect();
		 
		 
		 List<Pays> ps = new ArrayList<>();
         // test avec select
         Statement stm;
		try {
			stm = c.createStatement();
			
			String sql = "select * from pays";
	        ResultSet rs = stm.executeQuery(sql);
	      
	        
	        while (rs.next()){
	        	int nbhabitant = rs.getInt("nbhabitant");
	        	Pays p = new Pays(rs.getString("nom"));
	        	
	        	p.setNum(rs.getInt("num"));
	        	p.setNbHabitants(nbhabitant);
	        	
	        	ps.add(p);
	        }
	        rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ps;
        
		 
	 }
	 
	 public static Pays getPays(int numPays){
		 Pays p = null;
		 Connection c = Connect.cConnect();
		  Statement stm;
			try {
				stm = c.createStatement();
				
				String sql = "select * from pays WHERE num=" + numPays;
		        ResultSet rs = stm.executeQuery(sql);
		      
		        
		        while (rs.next()){
		        	p = new Pays(rs.getString("nom"));
		        	p.setNum(rs.getInt("num"));
		        	p.setNbHabitants(rs.getInt("nbhabitant"));
		        	
		        }
		        rs.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return p;
	 }
	 
	 public static List<Ville> getVilles(int numPays){
		 
		 Pays p = getPays(numPays);
		 
		 
		 String sql = "SELECT ville.num, ville.nom, ville.nbhabitants FROM ville " +
				 " INNER JOIN ville_pays ON (ville_pays.num_ville = ville.num )" +
				"  WHERE ville_pays.num_pays =" + numPays;
		 
		 Connection c = Connect.cConnect();
		 
		 List<Ville> villes = new ArrayList<>();
		   Statement stm;
			try {
				stm = c.createStatement();
				
		        ResultSet rs = stm.executeQuery(sql);
		      
		        
		        while (rs.next()){
		        	int nbhabitant = rs.getInt("nbhabitants");
		        	String nomVille = rs.getString("nom");
		        	int numVille = rs.getInt("num");
		        	
		        	Ville v = new Ville();
		        	v.setNom(nomVille);
		        	v.setNbHabitants(nbhabitant);
		        	v.setNumVille(numVille);
		        	v.setPays(p);
		        	
		        	
		        	villes.add(v);
		        }
		        rs.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return villes;
	 }
	 
	 public static void createPays(Pays p) {
		 
		 Connection c = Connect.cConnect();
	
		 PreparedStatement stm;
		try {
			
			c.setAutoCommit(false);
			
			stm = c.prepareStatement("INSERT INTO pays (nom, nbhabitant) VALUES (?,?)");
			stm.setString(1, p.getNom());
			stm.setInt(2, p.getNbHabitants());
			
			stm.execute ();
		
				
			c.commit();// c'est ici que l'on valide la transaction
		    c.setAutoCommit(true);
			
		    stm.close();
		    
  
		    
		} catch (SQLException e) {
			
			try{c.rollback();}catch(Exception e1){}
			
		
			e.printStackTrace();
		}	
		 
		 
	 }
}
