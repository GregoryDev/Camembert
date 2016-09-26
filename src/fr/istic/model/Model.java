package fr.istic.model;

import java.util.HashMap;
import java.util.Map;

import fr.istic.data.Data;

public class Model implements IModelAdapterController, IModelView{
	
	/*
	 * Le modele implemente deux interfaces. IModelView sera donnee aux vues,
	 * tandis que IModelAdapterController sera donnee a l'adapter et au controller.
	 */
	
	private Map<String, Data> items = new HashMap<String, Data>();
	
	
	// Pour ajouter un item on a besoin de creer un nouveau Data
	// qui contient uniquement la description et la valeur
	// La cle de la map correspond au nom de l'item
	@Override
	public void addItem(String name, String desc, float val){
		Data data = new Data(desc, val);
		items.put(name, data);
	}
	
	// On supprime l'item en se servant de la cle de la map
	@Override
	public void removeItem(String name){
		items.remove(name);
	}
	
	//Pour modifier une cle de la map, on recupere d'abord les donnees
	// associees, puis on ajoute ces donnees avec le nouveau nom
	@Override
	public void setItem(String newName, String oldName){
		Data oldData = items.get(oldName);
		removeItem(oldName);
		addItem(newName, oldData.getDescription(), oldData.getValue());
	}

	@Override
	public void setDescription(String k, String desc) {
		items.get(k).setDescription(desc);		
	}

	@Override
	public void setValue(String k, float val) {
		items.get(k).setValue(val);
	}

	// Retourne une map contenant tous les noms des items ainsi
	// que sa valeur associee en pourcentage
	@Override
	public Map<String, Float> getData() {
		float sum = getSum();
		Map<String, Float> res = new HashMap<String, Float>();
		for(String current : items.keySet()){
			float percent = (items.get(current).getValue()/sum)*100;
			res.put(current, percent);
		}
		return res;
	}
	
	// Retourne la somme totale des valeurs
	@Override
	public float getSum(){
		float sum = 0;
		for(String current : items.keySet()){
			sum += items.get(current).getValue();
		}
		return sum;
	}

	@Override
	public String getDescription(String key) {
		if(!items.containsKey(key))
			return "";
			
		return items.get(key).getDescription();
	}

	@Override
	public float getValue(String key) {
		if(!items.containsKey(key))
			return 0;
		
		return items.get(key).getValue();
	}

	// Retourne un taleau contenant toutes les cles 
	@Override
	public String[] getKeys() {
		String[] res = new String[items.size()];
		int i = 0;
		for(String k : items.keySet()) {
			res[i] = k;
			i++;
		}
		return res;
	}

	// Defintion des noms de colonnes pour la JTable
	@Override
	public String[] getColumnName() {
		String[] res = {"Nom", "Valeur", "Description"};
		return res;
	}
	
	
}
