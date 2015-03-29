package com.lionel.gereville.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.lionel.gereville.model.Ville;
import com.lionel.gereville.model.VilleTableModel;
import com.lionel.gereville.ui.UIGereville.UIGerevilleEventsListener;

public class UIlistVille{

	
	private JTable table;
	private VilleTableModel villeModel;
	private UIGerevilleEventsListener listener;

	public UIlistVille(){
		
		table = new JTable();
		
		
		table.setModel(villeModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //seulement une selection est possible
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() ==2){
					int row = table.getSelectedRow();
					
					listener.frmMainSelectedVilleEvent(villeModel.getVille(row));
				}
				
			}
		});
		//addMouseListener(this);
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_DELETE){
					int reponse = JOptionPane.showConfirmDialog(null, "sure ?");
					if (reponse==JOptionPane.YES_OPTION){
						int row = table.getSelectedRow();
						listener.frmMainDeleteVilleEvent(villeModel.getVille(row));
					}
				}
			}

			
		});
	}
	
	public JTable getView(){
		return  this.table;
	}


	public void addListener(UIGerevilleEventsListener listener){
		this.listener = listener;
	}
	
	public void afficherListe(List<Ville> villes){
		villeModel.addAllVille(villes);
	}
	
	public void addVille(Ville v){
		villeModel.addVille(v);
	}
	
	public void updateVille(Ville v){
		villeModel.updateVille(v);
	}
	/**
	 * clear all data
	 */
	public void clear(){
		villeModel.clear();
	}
	

	
	
}
