package pl.edu.wat.msk.elements;

import java.util.Vector;

import pl.edu.wat.msk.Notification;

/**
 * Komponent budowanego modelu.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.1
 */
public interface IModelComponent {

	/** 
	 *  Przetwarzanie
	 */
	public void processing(Notification notification);
	
	/** 
	 *  Metoda walidacji komponentu.
	 *  Zwraca kolekcję ewentualnych problemów spotykanych przy walidacji.
	 */
	public Vector<ValidationMessage> validate();

}