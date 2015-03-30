package com.lionel.gereville.ihm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.lionel.gereville.dao.Connect;
import com.lionel.gereville.dao.VilleDAO;
import com.lionel.gereville.model.Ville;

import javax.sql.rowset.WebRowSet;

import com.sun.rowset.WebRowSetImpl;

public class UIlistVille extends JTable implements MouseListener, KeyListener{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VilleTableModel villeModel = new VilleTableModel();
	private UIGerevilleEventsListener listener;

	public UIlistVille(){
		setModel(villeModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //seulement une selection est possible
		addMouseListener(this);
		addKeyListener(this);
	}

	public void addListener(UIGerevilleEventsListener listener){
		this.listener = listener;
	}
	
	public void afficherListe(List<Ville> villes){
		
		for (Ville v: villes){
			villeModel.addVille(v);
		}
	}
	
	public void clear(){
		villeModel.clear();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() ==2){
			int row = getSelectedRow();
			
			listener.onSelectedVille(villeModel.getVille(row));
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode()==KeyEvent.VK_DELETE)
		{
			int reponse = JOptionPane.showConfirmDialog(this, "vous êtes sur de vouloir supprimer une ville ?");
			if (reponse==JOptionPane.YES_OPTION)
			{
				int row = getSelectedRow();
				
				listener.onDeleteVille(villeModel.getVille(row));
				
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
