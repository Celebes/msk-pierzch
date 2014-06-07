package pl.edu.wat.msk.bramki_logiczne;

import java.util.List;
import java.util.Random;

import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.smo_generic.LogicGateGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * 
 * @author Krzysztof Jedynak
 * 
 */
public class AND extends LogicGateGeneric {

    
	public AND(String id, String rodzaj) {
		super(id, rodzaj);
	}
	
	   @Override
	    public void processing(ZgloszenieGeneric zgl, String id) {

	    }
	

}
