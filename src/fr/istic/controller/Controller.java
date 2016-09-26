package fr.istic.controller;

import fr.istic.model.IModelAdapterController;

public class Controller implements IModelAdapterController{
	
	//Le controller ne fait que passer les informations a l'adapter
	
	private IModelAdapterController adapter;
	
	public Controller(IModelAdapterController adapter){
		this.adapter = adapter;
	}

	@Override
	public void addItem(String name, String desc, float val) {
		adapter.addItem(name, desc, val);	
	}

	@Override
	public void removeItem(String name) {
		adapter.removeItem(name);		
	}

	@Override
	public void setItem(String newName, String oldName) {
		adapter.setItem(newName, oldName);		
	}

	@Override
	public void setDescription(String k, String desc) {
		adapter.setDescription(k, desc);		
	}

	@Override
	public void setValue(String k, float val) {
		adapter.setValue(k, val);		
	}
	
}
