package pl.edu.wat.msk.elements;

import java.util.Vector;

import pl.edu.wat.msk.Notification;

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
	public void processing(Notification notification) {
		if(nexts.isEmpty()) { // zwykłe destination
			//TODO
		}
		else { //destination wewnątrz aktywności złożonej
			//TODO
		}
	}

}