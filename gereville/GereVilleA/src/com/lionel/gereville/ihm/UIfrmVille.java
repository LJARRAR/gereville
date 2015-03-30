package com.lionel.gereville.ihm;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

import com.lionel.gereville.model.Pays;
import com.lionel.gereville.model.Ville;

/**
 * <p>
 * Titre :
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Copyright : Copyright (c) 2002
 * </p>
 * <p>
 * Soci√©t√© :
 * </p>
 * 
 * @author non attribu√©
 * @version 1.0
 */

public class UIfrmVille extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblSaisieTitle = new JLabel();
	private JLabel lblVilleNom = new JLabel();
	private JComboBox<Pays> cbPays = new JComboBox<>();
	private JLabel lblVillePays = new JLabel();
	private JTextField txtVilleNom = new JTextField();
	//private JTextField txtVillePays = new JTextField();
	private JTextField txtNbHabitants = new JTextField();
	private JLabel lblNbHabitants = new JLabel();
	private JCheckBox cbIsCapitale = new JCheckBox();
	private JLabel jLabel5 = new JLabel();
	private JButton btnRetour = new JButton();
	private JButton btnValider = new JButton();
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	
	private Ville currentVille = null; 

	private List<UIfrmVilleEventsListener> listeners = new ArrayList<UIfrmVilleEventsListener>();

	public UIfrmVille() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		jbInit();
		

		
	}

	public void addListener(UIfrmVilleEventsListener listener) {
		listeners.add(listener);
	}

	public void clear(){
		currentVille = null;
		cbIsCapitale.setSelected(false);
		txtNbHabitants.setText("");
		txtVilleNom.setText("");
		cbPays.setSelectedItem(-1);
	}
	
	// Initialiser le composant
	private void jbInit() {

		this.setSize(new Dimension(465, 302));
		this.setTitle("Saisie d'une ville");

		contentPane = new JPanel();
		contentPane.setLayout(gridBagLayout1);

		lblSaisieTitle.setFont(new java.awt.Font("SansSerif", 1, 18));
		lblSaisieTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaisieTitle.setText("Saisie d\'une ville");

		lblVilleNom.setFont(new java.awt.Font("SansSerif", 0, 16));
		lblVilleNom.setText("Nom");

		lblVillePays.setFont(new java.awt.Font("SansSerif", 0, 16));
		lblVillePays.setText("Pays");
		
		cbPays.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pays p = (Pays) cbPays.getSelectedItem();
				if (p!=null) {
					if (p.getCapitale()!=null){
						cbIsCapitale.setEnabled(false);
					}else{
						cbIsCapitale.setEnabled(true);
					}
				}
			}
		});

		lblNbHabitants.setFont(new java.awt.Font("SansSerif", 0, 16));
		lblNbHabitants.setToolTipText("");
		lblNbHabitants.setHorizontalAlignment(SwingConstants.LEFT);
		lblNbHabitants.setText("Nombre");
		lblNbHabitants.setVerticalAlignment(SwingConstants.TOP);
		lblNbHabitants.setVerticalTextPosition(SwingConstants.TOP);

		cbIsCapitale.setFont(new java.awt.Font("SansSerif", 0, 16));
		cbIsCapitale.setToolTipText("");
		cbIsCapitale.setText("Capitale");
		cbIsCapitale.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cbIsCapitale_clicked(e);
			}
		});

		jLabel5.setFont(new java.awt.Font("SansSerif", 0, 16));
		jLabel5.setText("d\'habitants");

		
		btnRetour.setText("Retour");
		btnRetour.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRetour_clicked(e);
			}
		});

		btnValider.setToolTipText("");
		btnValider.setText("Valider");
		btnValider.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnValider_clicked(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		contentPane.add(txtVilleNom, new GridBagConstraints(1, 1, 1, 1, 1.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(12, 12, 0, 0), 108, 4));
		contentPane.add(cbPays, new GridBagConstraints(1, 2, 1, 1, 1.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(10, 12, 0, 0), 108, 2));
		
		contentPane.add(txtNbHabitants, new GridBagConstraints(1, 3, 1, 2, 1.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(26, 12, 13, 0), 108, 3));
		
		contentPane.add(cbIsCapitale, new GridBagConstraints(2, 3, 1, 2, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(28, 54, 11, 67), 5, -6));
		contentPane.add(lblVilleNom, new GridBagConstraints(0, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(10, 38, 4, 32), 28, 0));
		contentPane.add(lblVillePays, new GridBagConstraints(0, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(7, 37, 3, 30), 29, 0));
		contentPane.add(lblNbHabitants, new GridBagConstraints(0, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(13, 34, 1, 31), 10, 3));
		contentPane.add(jLabel5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						35, 0, 11), 10, 2));
		contentPane.add(btnRetour, new GridBagConstraints(2, 7, 1, 2, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(15, -2, 28, 107), 32, 5));
		contentPane.add(btnValider, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0,
				GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(
						15, 45, 1, 97), 42, 5));
		contentPane.add(lblSaisieTitle, new GridBagConstraints(0, 0, 3, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(12, 46, 0, 52), 219, 5));
		
		setContentPane(contentPane);
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.setVisible(false);
		}
	}

	void cbIsCapitale_clicked(ChangeEvent e) {
		if (cbIsCapitale.isSelected()) {
			
		} else{
			
		}
	}

	void btnRetour_clicked(ActionEvent e) {
		this.setVisible(false);
	}

	// bouton valider
	void btnValider_clicked(ActionEvent e) throws Exception {

	
		// controles
		if (txtVilleNom.getText().equals("") || cbPays.getSelectedIndex() <0) {
			
			JOptionPane.showMessageDialog(this, "Veuillez saisir le nom et le pays de la ville", "Erreur saisie", JOptionPane.WARNING_MESSAGE);
			return;
		} 
		
		
		int nbHabitants = 0;
		try {
			nbHabitants = Integer.parseInt(txtNbHabitants.getText());
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,	"Nombre d'habitants : Champ numÈrique ou vide obligatoire","Erreur saisie",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		Pays p =  (Pays) cbPays.getSelectedItem();

		Ville v = new Ville(txtVilleNom.getText(), p, nbHabitants);
		if (cbIsCapitale.isSelected()){
			
			p.setCapitale(v);

		}
		
		
		//execution des ÈvËnements
		for (UIfrmVilleEventsListener events: listeners){
			events.onNewVille(v);
		}
			

		//this.setVisible(false);
	}
	
	public void afficherPays(List<Pays> paysliste){
		cbPays.removeAllItems();
		for (Pays p: paysliste){
			cbPays.addItem(p);
		}
		
	}
	
	public void afficherVille(Ville v)
	{
		
		currentVille = v;
		txtVilleNom.setText(v.getNom());
		cbPays.setSelectedItem(v.getPays());
		txtNbHabitants.setText(String.valueOf(v.getNbHabitants()));
	
	}

	public void displayErrorMessage(String msg){
		JOptionPane.showMessageDialog(this, msg, "Erreur saisie", JOptionPane.ERROR_MESSAGE);
	}

}
