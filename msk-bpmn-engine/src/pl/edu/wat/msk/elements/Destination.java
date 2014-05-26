package pl.edu.wat.msk.elements;

import java.util.Vector;

import pl.edu.wat.msk.Notification;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * Cel dla zgłoszeń obsługiwanych przez system.
 * Następuje tutaj utylizacja obiektów zgłoszeń.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 *
 */
public class Destination extends HaveNext {

	public Destination() {
	}
	
	/**
	 * Konstruktor dla przykładów dla destination wewnątrz aktywności złożonej.
	 * @param next
	 */
	public Destination(IModelComponent next) {
		nexts.add(next);
	}
	
	@Override
	public Vector<ValidationMessage> validate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl) {
		if(nexts.isEmpty()) { // zwykłe destination
			//TODO
		}
		else { //destination wewnątrz aktywności złożonej
			//TODO
		}
	}

	@Override
	public void putToNexts(ZgloszenieGeneric zgl) {
		// TODO Auto-generated method stub
		
	}

}