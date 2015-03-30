package com.lionel.gereville.ihm;

import java.sql.SQLException;

import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;

public interface UIGerevilleEventsListener {
	
	
	public void onBtnExitClicked();
	public void onSelectedPays(Pays pays);
	public void onBtnNewVilleClicked();
	public void onSelectedVille(Ville v);
	public void onDeleteVille(Ville v);
	public void onBtnNewPaysClicked();
	

}
