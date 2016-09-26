package fr.istic.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Descriptif extends JPanel implements IDescriptif {
	
	private static final long serialVersionUID = 1L;
	private ICamembert cam;
	private JLabel texte = new JLabel();
	private JButton avancer = new JButton("Avancer");
	private JButton reculer = new JButton("Reculer");
	private JButton supprimer = new JButton("Supprimer");
	private JButton ajouter = new JButton("Ajouter");
	private JLabel textKey = new JLabel("Nom");
	private JLabel textValue = new JLabel("Valeur");
	private JLabel textDescription = new JLabel("Description");
	private JTextArea key = new JTextArea();
	private JTextArea value = new JTextArea();
	private JTextArea description = new JTextArea();
	
	public Descriptif () {

		this.setLayout(new GridLayout(4,3));
		
		//Associe a chaque bouton une fonction
		
		avancer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cam.avancerSelection();
			}
			
		});
		
		reculer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cam.reculerSelection();
			}
			
		});
		
		supprimer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cam.supprimerItem();
			}
			
		});
		
		ajouter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cam.addItem(key.getText(), description.getText(), value.getText());				
			}
			
		});
		
		// AJout de tous les boutons
		add(avancer);
		add(texte);
		add(reculer);
		add(textKey);
		add(textValue);
		add(textDescription);
		add(key);
		add(value);
		add(description);
		add(ajouter);
		add(supprimer);
		textKey.setHorizontalAlignment(JLabel.CENTER);
		textValue.setHorizontalAlignment(JLabel.CENTER);
		textDescription.setHorizontalAlignment(JLabel.CENTER);
		key.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		value.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		description.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	@Override
	public void setCamembert(ICamembert cam){
		this.cam = cam;
	}
	
	@Override
	public void setDescription(String desc){
		texte.setHorizontalAlignment(JLabel.CENTER);
		texte.setText(desc);
	}
	
	@Override
	public void resetFields(){
		key.setText("");
		description.setText("");
		value.setText("");
	}
	
	// Gere l'activation et la desactivation des boutons
	@Override
	public void repaint() {
		if (cam != null) {
			if (cam.getSelection().equals("")) {
				avancer.setEnabled(false);
				reculer.setEnabled(false);
				supprimer.setEnabled(false);
				key.setEnabled(true);
				value.setEnabled(true);
				description.setEnabled(true);
				ajouter.setEnabled(true);
			} else {
				avancer.setEnabled(true);
				reculer.setEnabled(true);
				supprimer.setEnabled(true);
				key.setEnabled(false);
				value.setEnabled(false);
				description.setEnabled(false);
				ajouter.setEnabled(false);
			}
		}
		super.repaint();
	}

}
