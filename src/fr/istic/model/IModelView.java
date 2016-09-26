package fr.istic.model;

import java.util.Map;

public interface IModelView {
	
	public Map<String, Float> getData();
	public float getSum();
	public String[] getKeys();
	public String getDescription(String key);
	public float getValue(String key);
	public String[] getColumnName();

}
