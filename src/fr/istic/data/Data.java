package fr.istic.data;

public class Data {
	
	private String description;
	private float value;
	
	public Data(String d, float v){
		this.description = d;
		this.value = v;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public float getValue(){
		return this.value;
	}
	
	public void setDescription(String d){
		this.description = d;
	}
	
	public void setValue(float v){
		this.value = v;
	}
}
