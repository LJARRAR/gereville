package com.lionel.gereville.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class VilleTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	private List<Ville> villes = new ArrayList<>(0);
	
	private String[] columnsHeader = {"nom", "nbhabitant", "pays" };

	@Override
	public int getRowCount() {
		return villes.size();
	}

	@Override
	public int getColumnCount() {
		return columnsHeader.length;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnsHeader[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Ville v = villes.get(rowIndex);
		switch (columnIndex){
			case 0: return v.getNom();
			case 1: return v.getNbHabitants();
			case 2: return v.getPays().getNom();
		}
		return null;
	}
	
	public Ville getVille(int rowIndex){
		return villes.get(rowIndex);
	}
	
	public void addVille(Ville v){
		villes.add(v);
		
		fireTableRowsInserted(villes.size()-1, villes.size()-1);
	}
	
	public void setVilles(List<Ville> villes){
		this.villes = villes;
		//reconstruct all
		fireTableDataChanged();
	}
	
	
	public void clear(){
		villes.clear();
		
		fireTableDataChanged();
	}

}
