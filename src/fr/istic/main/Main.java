package fr.istic.main;

import java.util.Observer;

import fr.istic.adapter.Adapter;
import fr.istic.controller.Controller;
import fr.istic.model.IModelAdapterController;
import fr.istic.model.IModelView;
import fr.istic.model.Model;
import fr.istic.view.Frame;
import fr.istic.view.TableCamembert;
import fr.istic.view.View;

public class Main {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.addItem("Loyer", "sans charges", 400);
		model.addItem("Internet", "internet", 20);
		model.addItem("Charges", "eau, elec", 60);
		model.addItem("Provisions", "Nourriture...", 100);
		model.addItem("Essence", "Voyages", 60);		
		
		Adapter adapter = new Adapter((IModelAdapterController) model);
		IModelAdapterController controller = new Controller((IModelAdapterController) adapter);
		
		View view = new View((IModelView) model, controller);		
		TableCamembert table = new TableCamembert((IModelView) model, controller);
		
		new Frame(view, table);
		
		adapter.addObserver((Observer) view);
		adapter.addObserver((Observer) table);

	}

}
