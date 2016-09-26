package fr.istic.model;

public interface IModelAdapterController {

	public void addItem(String name, String desc, float val);
	public void removeItem(String name);
	public void setItem(String newName, String oldName);
	public void setDescription(String k, String desc);
	public void setValue(String k, float val);
}
