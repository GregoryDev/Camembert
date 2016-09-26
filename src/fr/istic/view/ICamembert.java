package fr.istic.view;

public interface ICamembert {

	public void avancerSelection();
	
	public void reculerSelection();
	
	public void supprimerItem();
	
	public void setDescriptif(IDescriptif desc);
	
	public String getSelection();

	public void addItem(String key, String desscription, String value);
}
