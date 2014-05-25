package pl.edu.wat.msk.elements;

import pl.edu.wat.msk.Notification;
import dissimlab.simcore.BasicSimObj;

/**
 * Komponent budowanego modelu.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.1
 */
public abstract class ModelComponent extends BasicSimObj implements IModelComponent {

	/**
	 * Nazwa komponentu określona przez użytkownika.
	 */
	private String name;

	/**
	 * Opis komponentu określony przez użytkownika.
	 */
	private String description;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void processing(Notification notification) {
	}
}