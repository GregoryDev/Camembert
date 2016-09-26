package fr.istic.view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import fr.istic.model.IModelAdapterController;
import fr.istic.model.IModelView;

public class TableCamembert extends AbstractTableModel implements Observer{

	private static final long serialVersionUID = 1L;
	private IModelView model;
	private IModelAdapterController controller;
	private String[] keys;
	
	public TableCamembert(IModelView model, IModelAdapterController controller){
		this.model = model;
		this.controller = controller;
	}
	
	//Met a jour la table en cas de notification recue de l'Observable
	@Override
	public void update(Observable arg0, Object arg1) {
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int i){
		return model.getColumnName()[i];
	}

	@Override
	public int getColumnCount() {
		return model.getColumnName().length;
	}

	@Override
	public int getRowCount() {
		return model.getKeys().length;
	}

	//Recuperation des donnees depuis le modele 
	@Override
	public Object getValueAt(int arg0, int arg1) {
		keys = model.getKeys();
		switch(arg1){
			case 0:
				return keys[arg0];
			case 1:
				return model.getValue(keys[arg0]);
			case 2:
				return model.getDescription(keys[arg0]);
			default: 
				return null;
		}
	}
	
	//Autorise la modification des cellules
	@Override
	public boolean isCellEditable(int i, int j){
		return true;
	}
	
	//Informe le controller des changements des cellules
	@Override
	public void setValueAt(Object v, int i, int j){
		switch(j){
		case 0:
			controller.setItem((String) v, keys[i]);
			break;
		case 1:
			controller.setValue(keys[i], Float.parseFloat((String)v));
			break;
		case 2:
			controller.setDescription(keys[i], (String) v);
			break;
		default: 
			
	}
	}
}
