package com.lionel.gereville.controller;

import java.util.List;

import com.lionel.gereville.dao.PaysDAO;
import com.lionel.gereville.dao.VilleDAO;
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

	
	
	public GerevilleController( UIGereville uiGereville,UIfrmVille frmVille, UIfrmPays frmPays ) {
		
		this.uiGereville = uiGereville;
		this.frmVille = frmVille;
		this.frmPays = frmPays;
		
		 
	}
	
	public void init(){
		
		uiGereville.afficheListePays(PaysDAO.getPays());
	}


	


	@Override
	public void onBtnExitClicked() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onSelectedPays(Pays pays) {
		uiGereville.clearListVilles();
		if (pays.getNom().equals("TOUS")){
			
			for (Pays p: PaysDAO.getPays()){
				List<Ville> villes = PaysDAO.getVilles(p.getNum());
				
				uiGereville.afficherVilles(villes);
			}
			
			
	
		}else{
			List<Ville> villes = PaysDAO.getVilles(pays.getNum());
			uiGereville.afficherVilles(villes);
		}
		
	}





	@Override
	public void onBtnNewVilleClicked() {
		
		frmVille.clear();
		frmVille.afficherPays(PaysDAO.getPays());
	    frmVille.setVisible(true);
		
	}





	@Override
	public void onCancelClicked() {
		frmVille.setVisible(false);
		
	}





	@Override
	public void onNewVille(Ville v) {
		//ajouter la ville au pays
		Pays pays = v.getPays();
		//already exist ?
		if( pays.getVilles().contains(v)){
			frmVille.displayErrorMessage("Ville " + v + " existe déjà");
		}else{
	//		pays.addVille(v);
			VilleDAO.createVille(v);
			uiGereville.selectPays(v.getPays()); //on indique qu'on veut afficher le pays en cours
			frmVille.setVisible(false);
		}
		
		
	}

	@Override
	public void onSelectedVille(Ville v) {
		frmVille.clear();
		frmVille.afficherPays(PaysDAO.getPays());
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
		PaysDAO.createPays(p);
		uiGereville.afficheListePays(PaysDAO.getPays());
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
		VilleDAO.deleteVille(v);
		uiGereville.afficheListePays(PaysDAO.getPays());
		
	}






	
	
	
	
	
	

}
