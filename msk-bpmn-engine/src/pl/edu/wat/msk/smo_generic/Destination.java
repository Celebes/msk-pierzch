package pl.edu.wat.msk.smo_generic;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;

/**
 * Cel dla zgłoszeń obsługiwanych przez system.
 * Następuje tutaj utylizacja obiektów zgłoszeń.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 *
 */
public class Destination extends HavePrevNext {
	private SimSystem simSystem;
	
	public Destination() {
		simSystem = SimSystem.getInstance();
	}
	
	/**
	 * Konstruktor dla przykładów dla destination wewnątrz aktywności złożonej.
	 * @param next
	 */
	public Destination(IModelComponent next) {
		nexts.add(next);
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {
		System.out.println("Zgloszenie " + zgl.getTenNr() +  " opuszcza system z sukcesem");
		simSystem.success(zgl);
	}

	@Override
	public void putToNexts(ZgloszenieGeneric zgl) {
	}

}