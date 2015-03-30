package com.lionel.gereville.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;

public class PaysDAOTest {

	@Test
	public void testGetPays() {
		List<Pays> pays = PaysDAO.getPays();
		assertNotNull(pays);
		assertFalse(pays.isEmpty());
		Pays p = pays.get(0);
		assertEquals("FRANCE", p.getNom());
	}
	@Test
	public void testGetPaysList() {
		List<Pays> pays = PaysDAO.getPays();
		assertNotNull(pays);
		assertFalse(pays.isEmpty());
		Pays p = pays.get(0);
		assertEquals("FRANCE", p.getNom());
	}
	
	@Test
	public void testCreatePays(){
		Pays p = new Pays("BELgIQUE");
		PaysDAO.createPays(p);		
	}	
	
	@Test
	public void testGetVille() {
		List<Ville> villes = PaysDAO.getVilles(1);
		assertNotNull(villes);
		assertFalse(villes.isEmpty());
		Ville v = villes.get(0);
		assertEquals("PARIS", v.getNom());
	}

	@Test
	public void testCreateVille(){
		Ville v = new Ville("New-York", PaysDAO.getPays(2));
		PaysDAO.createVille(v);
	}
	

}
