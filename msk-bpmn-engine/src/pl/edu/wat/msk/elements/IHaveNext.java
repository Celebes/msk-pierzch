package pl.edu.wat.msk.elements;

import java.util.ArrayList;

import pl.edu.wat.msk.Notification;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.2
 *
 */
public interface IHaveNext {

	public ArrayList<IModelComponent> getNext();
	
	public void setNext(ArrayList<IModelComponent> next);

	public void addNext(IModelComponent next);
	
	public void removeNext(IModelComponent next);
	
	/**
	 * Podaje zgłoszenie do następników.
	 * @param notification
	 */
	public void putToNexts(ZgloszenieGeneric zgl);
}