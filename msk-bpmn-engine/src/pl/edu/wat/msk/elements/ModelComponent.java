package pl.edu.wat.msk.elements;
import dissimlab.simcore.BasicSimObj;

/**
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
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
}