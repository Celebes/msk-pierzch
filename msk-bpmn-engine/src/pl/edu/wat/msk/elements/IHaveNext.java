package pl.edu.wat.msk.elements;

import java.util.ArrayList;
import java.util.List;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.2
 *
 */
public interface IHaveNext {

	public List<IModelComponent> getNext();
	
	public void setNext(List<IModelComponent> next);

	public void addNext(IModelComponent next);
	
	public void removeNext(IModelComponent next);
	
	/**
	 * Podaje zgłoszenie do następników.
	 * @param notification
	 */
	public void putToNexts(ZgloszenieGeneric zgl);

}