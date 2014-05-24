package pl.edu.wat.msk.elements;

import java.util.Vector;

/**
 * 
 * @since 24.05.2014, 19:50
 * Generated by AgroUML
 *
 */
public interface IModelComponent {

	/** 
	 *  Przetwarzanie
	 */
	public void processing();
	
	/** 
	 *  Metoda walidacji komponentu.
	 *  Zwraca kolekcj? ewentualnych problem?w spotykanych przy walidacji
	 */
	public Vector validate();

}