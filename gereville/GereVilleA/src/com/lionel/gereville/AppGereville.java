package com.lionel.gereville;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.lionel.gereville.controller.GerevilleController;
import com.lionel.gereville.ihm.UIGereville;
import com.lionel.gereville.ihm.UIfrmPays;
import com.lionel.gereville.ihm.UIfrmVille;

public class AppGereville {

	private GerevilleController mainControler;
	private UIGereville mainUI;
	private UIfrmPays paysUI;
	private UIfrmVille villesUI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
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
		paysUI = new UIfrmPays();
		paysUI.setVisible(false);
		//ville form
		villesUI = new UIfrmVille();
		villesUI.setVisible(false);
		
		//create the main controller and bind uis to controller
		mainControler = new GerevilleController(mainUI, villesUI, paysUI);
		
		/**
		 * subscribe controller to ui events
		 */
		mainUI.addListener(mainControler);
		paysUI.addListener(mainControler);
		villesUI.addListener(mainControler);
		
		// Center frames
		centerFrame(mainUI);
		centerFrame(paysUI);
		centerFrame(villesUI);
		
		//initialize data
		mainControler.init();
		
		

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

}
