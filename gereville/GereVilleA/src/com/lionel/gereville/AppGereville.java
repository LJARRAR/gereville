package com.lionel.gereville;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import com.lionel.gereville.dao.GerevilleDAO;
import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;
import com.lionel.gereville.ui.UIGereville;
import com.lionel.gereville.ui.UIGereville.UIGerevilleEventsListener;
import com.lionel.gereville.ui.UIfrmPays;
import com.lionel.gereville.ui.UIfrmPays.UIfrmPaysEventsListener;
import com.lionel.gereville.ui.UIfrmVille;
import com.lionel.gereville.ui.UIfrmVille.UIfrmVilleEventsListener;

public class AppGereville implements UIGerevilleEventsListener, UIfrmVilleEventsListener, UIfrmPaysEventsListener{


	private UIGereville mainUI;
	private UIfrmVille frmVille;
	private UIfrmPays frmPays;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					AppGereville app = new AppGereville();
					app.mainUI.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public AppGereville() {
		initialize();
	}


	private void initialize() {
		
		/**
		 * init views
		 */
		//main window
		mainUI = new UIGereville();
		mainUI.setVisible(false);
		
		//pays form
		frmPays = new UIfrmPays();
		frmPays.setVisible(false);
				
		//ville form
		frmVille = new UIfrmVille();
		frmVille.setVisible(false);
		
		//subscribe controller to views
		mainUI.addListener(this);
		frmPays.addListener(this);
		frmVille.addListener(this);
		
		// Center main frame
		centerFrame(mainUI);

		/**
		 * initialize data
		 */
		
		mainUI.afficheListePays(GerevilleDAO.getPays());
		
		

	}
	
	private void centerFrame(JFrame ui){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = ui.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		ui.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		
	}

	@Override
	public void frmPaysNewPaysEvent(Pays p) {
		//TODO test if not exist
		GerevilleDAO.createPays(p);
		mainUI.afficheListePays(GerevilleDAO.getPays());
		frmPays.setVisible(false);
		
	}

	@Override
	public void frmVilleCancelEvent() {
		frmVille.setVisible(false);
		
	}

	@Override
	public void frmVilleNewVilleEvent(Ville v) {
		//TODO check if already exist
		try {
			GerevilleDAO.createVille(v);
		} catch (Exception e) {
			frmVille.displayErrorMessage(e.getMessage());
		}
				
		mainUI.selectPays(v.getPays());
		frmVille.setVisible(false);
		
	}

	@Override
	public void frmVilleUpdateVilleEvent(Ville v) {
		mainUI.selectPays(v.getPays()); //on indique qu'on veut afficher le pays en cours
		
	}

	@Override
	public void frmMainExitEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frmMainSelectedPaysEvent(Pays pays) {
		mainUI.clearListVilles();
		if (pays.getNom().equals("TOUS")){
			
			for (Pays p: GerevilleDAO.getPays()){
				List<Ville> villes = GerevilleDAO.getVilles(p.getNum());
				
				mainUI.afficherVilles(villes);
			}
			
			
	
		}else{
			List<Ville> villes = GerevilleDAO.getVilles(pays.getNum());
			mainUI.afficherVilles(villes);
		}
		
	}

	@Override
	public void frmMainNewVilleEvent() {
		centerFrame(frmVille);
		frmVille.clear();
		frmVille.afficherPays(GerevilleDAO.getPays());
	    frmVille.setVisible(true);
		
	}

	@Override
	public void frmMainSelectedVilleEvent(Ville v) {
		frmVille.clear();
		frmVille.afficherPays(GerevilleDAO.getPays());
		frmVille.afficherVille(v);
		frmVille.setVisible(true);
	}
	@Override
	public void frmMainDeleteVilleEvent(Ville v) {
		//TODO dao
		frmMainSelectedPaysEvent(v.getPays());
		
	}

	@Override
	public void frmMainNewPaysEvent() {
		frmPays.clear();
	    frmPays.setVisible(true);		
		
	}

}
