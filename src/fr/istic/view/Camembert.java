package fr.istic.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.Map;
import javax.swing.JComponent;
import fr.istic.model.IModelAdapterController;
import fr.istic.model.IModelView;


public class Camembert extends JComponent implements MouseListener, ICamembert{

	private static final long serialVersionUID = 1L;
	private Graphics2D g2d;	
	private IModelView model;
	private IModelAdapterController controller;
	private IDescriptif desc;
	
	private Map<String, Float> data;
	private String selection = "";
	
	public Camembert(IModelView im, IModelAdapterController ic) {
		model = im;
		controller = ic;
		addMouseListener(this);
	}
	
	public void setDescriptif(IDescriptif d){
		desc = d;
	}
	
	@Override
	public String getSelection() {
		return selection;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Dimension d = getSize();

		g2d = (Graphics2D) g;

		// TODO: utilisation des données du IModel pour l'affichage
		
		data = model.getData();
		float tmp = 0;
		
		//Affichage de tous les items
		for(String s : data.keySet()){
			
		    Arc2D arc = new Arc2D.Float(Arc2D.PIE);
		    
		    // Mise en évidence de l'item selectionné
		    if(selection == s)
		    	arc.setFrame(d.width/2-225, d.height/2-225, 450, 450);
		    else 
		    	arc.setFrame(d.width/2-200, d.height/2-200, 400, 400);
		    
		    arc.setAngleStart(tmp);
		    
		    //Sauvegarde de l'angle pour connaitre la postion de depart de la prochaine iteration
		    tmp += (data.get(s)/100*360);
		    
		    arc.setAngleExtent(data.get(s)/100*360);

		    //Couleur en fonction de la postion de l'item
		    g2d.setColor(new Color((int)(tmp*255/360), (int)(tmp*255/360), (int)(tmp*255/360)));
		    g2d.fill(arc);
		    
		    g2d.setFont(new Font("Arial", Font.PLAIN, 14));
		    g2d.setColor(Color.RED);
		    // Affichage du nom de l'item au milieu de l'arc correspondant mais avec un rayon plus grand
		    g2d.drawString(s, (int)((d.width/2)+((Math.cos(Math.toRadians(arc.getAngleStart()+arc.getAngleExtent()/2)))*250)), (int)((d.height/2)-((Math.sin(Math.toRadians(arc.getAngleStart()+arc.getAngleExtent()/2)))*250)));

		}
		//Second cerle afin de representer un donut
		g2d.setColor(getBackground());
		g2d.fillOval(d.width/2-100, d.height/2-100, 200, 200);
		
		g2d.setColor(Color.BLACK);
		
		String total = "Total : " + Float.toString(model.getSum()) + "€"; 
		g2d.drawString(total, (d.width/2)-(total.length()*4), d.height/2);
		
    	// Affichage description et prix si l'item est selectionne
    	sendData();
    	desc.repaint();
	}	
	
	private void sendData() {
		String res;
		if(selection == "")
			res = "";
		else {
			res = model.getDescription(selection);
			res += "; " + model.getValue(selection);
		}
		//Permet de mettre a jour la cellule affichant la description lorqu'un item est selectionne
		desc.setDescription(res);
	}
	
	@Override
	public void supprimerItem() {
		controller.removeItem(selection);
		selection = "";
	}
	
	@Override
	public void avancerSelection(){
		if(!selection.equals("")){
			String[] keys = model.getKeys();
			int i = 0;
			// On recherche l'indice correspondant a la selection
			for(String key : keys){
				if(key.equals(selection))
					break;
				i++;
			}
			//Si l'indice est le dernier, l'item suivant est le premier
			if(i == keys.length - 1)
				selection = keys[0];
			//Sinon on prend le suivant
			else
				selection = keys[i+1];
			//Actualisation de l'affichage
			repaint();
		}
	}
	
	@Override
	public void reculerSelection(){
		if(!selection.equals("")){
			String[] keys = model.getKeys();
			int i = 0;
			//Recherche de l'indice correspondant a la selection
			for (String key : keys) {
				if (key.equals(selection))
					break;
				i++;
			}
			//Si l'indice est le premier, l'item precedent est le dernier
			if (i == 0)
				selection = keys[keys.length-1];
			//Sinon on prend le precedent
			else 
				selection = keys[i-1];
			//Actualisation de l'affichage
			repaint();
		}
	}

	// Appelee lors de l'appui sur le bouton Ajouter de Descriptif
	// On ajoute uniquement si les champs ne sont pas vides
	@Override
	public void addItem(String key, String description, String value){
		if(!key.equals("") && !description.equals("") && !value.equals("")){
			controller.addItem(key, description, Float.parseFloat(value));
			desc.resetFields();
		}		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
				
		// TODO: vérifier si un quartier de camembert a été selectionné 
		// et renvoyer vers le controlleur		
			
		float debutQuart = 0;
		float finQuart = 0;
		for(String s : data.keySet()){
			// Parcours de donnees afin de calculer si le clic detecte
			// se trouve dans la zone d'un item
			selection = "";
			finQuart = debutQuart + (data.get(s)/100*360);
			float alpha = (float) Math.toDegrees(Math.atan2(arg0.getY() - getSize().height/2, arg0.getX() - getSize().width/2));
			
			alpha = (alpha < 0) ? (alpha * -1) : (360 - alpha);
			
			float dist = (float) Math.sqrt(Math.pow(getSize().width/2 - arg0.getX(), 2) + Math.pow(getSize().height/2 - arg0.getY(), 2));
			
			// Si oui on met a jour notre selection et on arrete la boucle
			if(alpha > debutQuart && alpha < finQuart && dist > 100 && dist < 200) {
				selection = s;
				break;
			}
			
			debutQuart = finQuart;
		}		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

