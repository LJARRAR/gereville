package com.lionel.gereville.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.lionel.gereville.model.Pays;

public class UIfrmPays extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblNom = new JLabel("Nom");
	private JTextField txtNom = new JTextField();
	private JLabel lblPresident = new JLabel("Pr�sident");
	private JTextField txtPresident = new JTextField();
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Retour");

	public interface UIfrmPaysEventsListener {
		public void frmPaysNewPaysEvent(Pays p);
	}

	private UIfrmPaysEventsListener listener;

	public UIfrmPays() {

		setTitle("saisie d'un pays");
		setBounds(100, 100, 450, 300);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelTop = new JPanel();
		panelTop.add(lblNom);
		txtNom.setPreferredSize(new Dimension(100, 20));
		panelTop.add(txtNom);
		contentPane.add(panelTop);
		
		JPanel panelMiddle = new JPanel();
		panelMiddle.add(lblPresident);
		txtPresident.setPreferredSize(new Dimension(100, 20));
		panelMiddle.add(txtPresident);
		contentPane.add(panelMiddle);

		JPanel panelBtn = new JPanel();
		panelBtn.add(btnOK);
		panelBtn.add(btnCancel);
		contentPane.add(panelBtn);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onValidForm();
			}
		});

	}

	public void addListener(UIfrmPaysEventsListener listener) {
		this.listener = listener;
	}

	private void onValidForm() {
		if (txtNom.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un nom de pays valide", "Erreur saisie",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Pays p = new Pays(txtNom.getText());
		p.setPresident(txtPresident.getText());

		// fire event to listener
		listener.frmPaysNewPaysEvent(p);
		

	}
	
	public void displayErrorMessage(String msg){
		JOptionPane.showMessageDialog(this, msg, "Erreur saisie", JOptionPane.ERROR_MESSAGE);
	}

	public void clear() {
		txtNom.setText("");
		txtPresident.setText("");
		
	}
	
	

}
