package com.lionel.gereville.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;

public class PaysDAOTest {

	@Test
	public void testGetPays() {
		List<Pays> pays = GerevilleDAO.getPays();
		assertNotNull(pays);
		assertFalse(pays.isEmpty());
		Pays p = pays.get(0);
		assertEquals("FRANCE", p.getNom());
	}
	
	@Test
	public void testGetVille() {
		List<Ville> villes = GerevilleDAO.getVilles(1);
		assertNotNull(villes);
		assertFalse(villes.isEmpty());
		Ville v = villes.get(0);
		assertEquals("PARIS", v.getNom());
	}
	@Test
	public void testCreate(){
		Pays p = new Pays("BELGIQUE");
		GerevilleDAO.createPays(p);
		
	}

}
