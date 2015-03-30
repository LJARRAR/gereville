package com.lionel.gereville.model;

import java.io.Serializable;

/*
 * <p>Titre : </p>
 * <p>Description : Classe Ville</p>
 * <p>Copyright : Copyright (c) 2002</p>
 * <p>Société : afpa</p>
 * @author : PF
 * @version 1.0
 **/

public class Ville implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numVille;
	protected String nom;
	protected Pays pays;
	protected int nbHabitants;
	
	// Constructeur par défaut
		public Ville() {
		}


		public Ville(String sNom, Pays sPays) {
			this.nom = sNom;
			this.pays = sPays;
		}

		public Ville(String sNom, Pays sPays, int dNbHabitants) {
			this.nom = sNom;
			this.pays = sPays;
			this.nbHabitants = dNbHabitants;
		}

		
		
		
	public int getNumVille() {
			return numVille;
		}


		public void setNumVille(int numVille) {
			this.numVille = numVille;
		}


	public String getNom() {
		return nom;
	}

	public int getNbHabitants() {
		return nbHabitants;
	}

	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	
	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numVille;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (numVille != other.numVille)
			return false;
		return true;
	}



	
	
	

}