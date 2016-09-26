package fr.istic.view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Frame extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	
	public Frame(View view, TableCamembert table){		
		
		// La Frame est composee de deux parties
		panel.setLayout(new GridLayout(1, 2));
		// A gauche la View qui contient le camembert plus les boutons
		panel.add(view);
		
		// A droite la JTable
		panel.add(new JScrollPane(new JTable(table)));
		
		add(panel);
		
		setTitle("The Interactive Camembert");
		setSize(1200,700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
