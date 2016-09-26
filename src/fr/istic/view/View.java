package fr.istic.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.istic.model.IModelAdapterController;
import fr.istic.model.IModelView;

public class View extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private Descriptif desc;
	private Camembert cam;
	public View(IModelView model, IModelAdapterController controller){
		// Seul Camembert possede le controller
		// lorsqu'un bouton est sollicite, celui ci 
		// appelle les methodes dans Camembert afin
		// d'appeler le controller
		cam = new Camembert(model, controller);
		desc = new Descriptif();
		cam.setDescriptif((IDescriptif) desc);
		desc.setCamembert((ICamembert) cam);
		
		// Placement des composants
		setLayout(new BorderLayout());
		add(cam, BorderLayout.CENTER);
		add(desc, BorderLayout.NORTH);
	}

	// Signal recu de l'Observable pour indiquer un changement
	@Override
	public void update(Observable arg0, Object arg1){
		cam.repaint();		
	}

}
