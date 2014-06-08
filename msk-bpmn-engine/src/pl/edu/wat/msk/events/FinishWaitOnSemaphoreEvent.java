package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.Semaphore;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * Zdarzenie zakończenia oczekiwania na semaforze.
 * @since 07.06.2014
 * @author Mariusz Kielan
 *
 */
public class FinishWaitOnSemaphoreEvent extends BasicSimEvent<Semaphore, ZgloszenieGeneric> {
	private ZgloszenieGeneric zgloszenie;
	
	public FinishWaitOnSemaphoreEvent(Semaphore parent, double delay, ZgloszenieGeneric zgl) throws SimControlException {
		super(parent, delay, zgl);
		
		this.zgloszenie = zgl;
	}

	@Override
	protected void stateChange() throws SimControlException {
		System.out.println(simTime()+": Koniec oczekiwania na semaforze zgl. nr: " + zgloszenie.getTenNr());
		getSimObj().putToNexts(zgloszenie);
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onInterruption() throws SimControlException {
		System.out.println(simTime()+": !Przerwanie oczekiwania na semaforze " + getSimObj().getId() +  " przy zgl. nr: " + transitionParams.getTenNr());	
	}

}
