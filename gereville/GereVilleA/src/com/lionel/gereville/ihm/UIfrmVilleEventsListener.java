package com.lionel.gereville.ihm;

import com.lionel.gereville.model.Ville;

public interface UIfrmVilleEventsListener {

	public void onCancelClicked();
	public void onNewVille(Ville v) throws Exception;
	public void onUpdatedVille(Ville v);
}
