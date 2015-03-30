package com.lionel.gereville;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.lionel.gereville.dao.Connect;
import com.lionel.gereville.dao.GerevilleDAO;
import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;
import com.lionel.gereville.ui.UIGereville;
import com.lionel.gereville.ui.UIGereville.UIGerevilleEventsListener;
import com.lionel.gereville.ui.UIfrmPays;
import com.lionel.gereville.ui.UIfrmPays.UIfrmPaysEventsListener;
import com.lionel.gereville.ui.UIfrmVille;
import com.lionel.gereville.ui.UIfrmVille.UIfrmVilleEventsListener;
import com.lionel.gereville.ui.UIlistVille;
import com.lionel.gereville.utils.EventQueueProxy;

public class AppGereville implements UIGerevilleEventsListener, UIfrmVilleEventsListener, UIfrmPaysEventsListener{


	private UIGereville mainUI;
	private UIfrmVille frmVille;
	private UIfrmPays frmPays;
	private UIlistVille listVilleUI;
	

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					
					AppGereville app = new AppGereville();
					//catch RuntimeExceptions during execution
					EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
					queue.push(new EventQueueProxy());
					
					
					
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

		//inner components
		//ville list
		listVilleUI = new UIlistVille();
		
		
		//main window
		mainUI = new UIGereville(listVilleUI.getView());
		mainUI.setVisible(false);
		
		//pays form
		frmPays = new UIfrmPays();
		frmPays.setVisible(false);
				
		//ville form
		frmVille = new UIfrmVille();
		frmVille.setVisible(false);
		
		
		//subscribe controller to components & views
		listVilleUI.addListener(this);
		mainUI.addListener(this);
		frmPays.addListener(this);
		frmVille.addListener(this);
		
		// Center main frame
		centerFrame(mainUI);
		centerFrame(frmVille);

		/**
		 * initialize data
		 */
		
		//villeModel = new VilleTableModel();
		
		
		//check if database connection is Ok
		try {
			Connect.cConnect();
			
			mainUI.afficheListePays(GerevilleDAO.getPays());
			
			
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(mainUI,"was unable to connect to database, please check that your dabase server is started, error is displayed in console ","Error",
		              JOptionPane.INFORMATION_MESSAGE);
			 
		}
		
	
		
		

		
		

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
			//if everything went fine update view list
			listVilleUI.addVille(v);
			//mainUI.selectPays(v.getPays());
			frmVille.setVisible(false);
		} catch (Exception e) {
			frmVille.displayErrorMessage(e.getMessage());
		}
		
		
	}

	@Override
	public void frmVilleUpdateVilleEvent(Ville v) {
		try {
			GerevilleDAO.updateVille(v);
			
			listVilleUI.updateVille(v); //notify model & view
			frmVille.setVisible(false);
		} catch (Exception e) {
			frmVille.displayErrorMessage(e.getMessage());
		}
		
		//mainUI.selectPays(v.getPays()); //on indique qu'on veut afficher le pays en cours
		
	}

	@Override
	public void frmMainExitEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frmMainSelectedPaysEvent(Pays pays) {
		listVilleUI.clear();
		if (pays.getNom().equals("TOUS")){
			
			//TODO not optimized 
			for (Pays p: GerevilleDAO.getPays()){
				List<Ville> villes = GerevilleDAO.getVilles(p.getNum());
				listVilleUI.afficherListe(villes);
				//mainUI.afficherVilles(villes);
			}
			
			
	
		}else{
			List<Ville> villes = GerevilleDAO.getVilles(pays.getNum());
			listVilleUI.afficherListe(villes);
			//mainUI.afficherVilles(villes);
		}
		
	}

	@Override
	public void frmMainNewVilleEvent() {
		//centerFrame(frmVille);
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
