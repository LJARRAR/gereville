package com.lionel.gereville.controller;

import java.util.List;

import com.lionel.gereville.dao.GerevilleDAO;
import com.lionel.gereville.ihm.UIGereville;
import com.lionel.gereville.ihm.UIGerevilleEventsListener;
import com.lionel.gereville.ihm.UIfrmPays;
import com.lionel.gereville.ihm.UIfrmPays.UIfrmPaysEventsListener;
import com.lionel.gereville.ihm.UIfrmVille;
import com.lionel.gereville.ihm.UIfrmVilleEventsListener;
import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;

public class GerevilleController implements UIGerevilleEventsListener, UIfrmVilleEventsListener, UIfrmPaysEventsListener{
	
	private UIGereville uiGereville;
	private UIfrmVille frmVille = null;
	private UIfrmPays frmPays = null;
	
	private int test =10;

	
	
	public GerevilleController( UIGereville uiGereville,UIfrmVille frmVille, UIfrmPays frmPays ) {
		
		this.uiGereville = uiGereville;
		this.frmVille = frmVille;
		this.frmPays = frmPays;
		
		 
	}
	
	public void init(){
		
		uiGereville.afficheListePays(GerevilleDAO.getPays());
	}


	


	@Override
	public void onBtnExitClicked() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onSelectedPays(Pays pays) {
		uiGereville.clearListVilles();
		if (pays.getNom().equals("TOUS")){
			
			for (Pays p: GerevilleDAO.getPays()){
				List<Ville> villes = GerevilleDAO.getVilles(p.getNum());
				
				uiGereville.afficherVilles(villes);
			}
			
			
	
		}else{
			List<Ville> villes = GerevilleDAO.getVilles(pays.getNum());
			uiGereville.afficherVilles(villes);
		}
		
	}





	@Override
	public void onBtnNewVilleClicked() {
		
		frmVille.clear();
		frmVille.afficherPays(GerevilleDAO.getPays());
	    frmVille.setVisible(true);
		
	}





	@Override
	public void onCancelClicked() {
		frmVille.setVisible(false);
		
	}





	@Override
	public void onNewVille(Ville v) {

		//TODO check if already exist
		try {
			GerevilleDAO.createVille(v);
		} catch (Exception e) {
			frmVille.displayErrorMessage(e.getMessage());
		}
		
		uiGereville.selectPays(v.getPays());
		frmVille.setVisible(false);
		
	}

	@Override
	public void onSelectedVille(Ville v) {
		frmVille.clear();
		frmVille.afficherPays(GerevilleDAO.getPays());
		frmVille.afficherVille(v);
		frmVille.setVisible(true);
	}

	@Override
	public void onUpdatedVille(Ville v) {
		uiGereville.selectPays(v.getPays()); //on indique qu'on veut afficher le pays en cours
	}





	@Override
	public void onNewPays(Pays p) {
		
//		if (listePays.contains(p)){
//			frmPays.displayErrorMessage( "Le pays existe déjà");
//		}else{
//			listePays.add(p);
//			uiGereville.afficheListePays(listePays);
//			frmPays.setVisible(false);
//		}
		//TODO test if not exist
		GerevilleDAO.createPays(p);
		uiGereville.afficheListePays(GerevilleDAO.getPays());
		frmPays.setVisible(false);
		
		
	}





	@Override
	public void onBtnNewPaysClicked() {
		frmPays.clear();
	    frmPays.setVisible(true);		
	}





	@Override
	public void onDeleteVille(Ville v) {
		//TODO
//		for (Pays p: listePays){
//			
//			List<Ville> vs = p.getVilles();
//			if (vs.contains(v)){
//				vs.remove(v);
//			}
//		}
		
		onSelectedPays(v.getPays());
		
	}






	
	
	
	
	
	

}
