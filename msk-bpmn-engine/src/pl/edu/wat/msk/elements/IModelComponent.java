package pl.edu.wat.msk.elements;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

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
	public void processing(ZgloszenieGeneric zgl, String id);
	

}