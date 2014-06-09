package pl.edu.wat.msk.smo_generic;

import pl.edu.wat.msk.distributions.IDistribution;
import pl.edu.wat.msk.distributions.Normal;
import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.events.WaitOnSemaphoreEvent;
import dissimlab.simcore.SimControlException;

/**
 * Semafor
 * 
 * @since 07.06.2014
 * @author Mariusz Kielan
 *
 */
public class Semaphore extends HavePrevNext {

	private IDistribution distribution;
	
	public Semaphore(String id) {
		this.id = id;
		distribution = new Normal(10.0, 1.0);
	}
	
	/**
	 * Semafor jeśli ustawiony będzie przed SMO to będzie tworzył zdarzenie oczekiwania na semaforze,
	 * które dalej będzie obsługiwało oczekiwanie i zakończenie oczekiwania.
	 * Jeśli następnik nie będzie typu SMO to przekaże zgłoszenie bezpośrednio do niego.
	 */
	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {
		super.processing(zgl, getId());
		
		if(getNext().get(0).getClass() == SmoFiniteGeneric.class) {
			//powołanie zdarzenia oczekiwania na semaforze
			
			try {
				new WaitOnSemaphoreEvent(this, distribution.getNextDouble(), zgl);
			} catch (SimControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			putToNexts(zgl);
		}
	}
}
