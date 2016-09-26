package fr.istic.adapter;

import java.util.Observable;

import fr.istic.model.IModelAdapterController;

public class Adapter extends Observable implements IModelAdapterController{
	
	/*
	 * L'adapteur etend Observable afin de pouvoir notifier les Observers a
	 * chaque changement. Les changements s'effectuant dans modele, la 
	 * methode setChanged() permet d'actionner la methode notifyObservers()
	 */
	
	private IModelAdapterController model;
	
	public Adapter(IModelAdapterController model){
		this.model = model;
	}

	@Override
	public void addItem(String name, String desc, float val) {
		model.addItem(name, desc, val);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void removeItem(String name) {
		model.removeItem(name);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void setItem(String newName, String oldName) {
		model.setItem(newName, oldName);
		this.setChanged();
		this.notifyObservers();		
	}

	@Override
	public void setDescription(String k, String desc) {
		model.setDescription(k, desc);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void setValue(String k, float val) {
		model.setValue(k, val);
		this.setChanged();
		this.notifyObservers();	
	}

}
