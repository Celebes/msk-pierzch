package pl.edu.wat.msk.events;

import pl.edu.wat.msk.distributions.IDistribution;
import pl.edu.wat.msk.distributions.Normal;
import pl.edu.wat.msk.smo_generic.Semaphore;
import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * Zdarzenie oczekiwania na semaforze.
 * Jeśli kolejka jest niepałna to przepuszcza to generuje zdarzenie zakończenia oczekiwania,
 * w przeciwnym wypadku tworzy kolejne zdarzenie oczekiwania.
 * 
 * @since 07.06.2014
 * @author Mariusz Kielan
 *
 */
public class WaitOnSemaphoreEvent extends BasicSimEvent<Semaphore, ZgloszenieGeneric>{

	private ZgloszenieGeneric notification;
	private IDistribution distribution;
	
	public WaitOnSemaphoreEvent(Semaphore parent, double delay, ZgloszenieGeneric zgl) throws SimControlException {
		super(parent, delay, zgl);
		notification = zgl;
		distribution = new Normal(10.0, 1.0);
	}

	@Override
	protected void stateChange() throws SimControlException {
		System.out.println(simTime()+": Oczekiwania na semaforze zgl. nr: " + notification.getTenNr());
		
		double time = distribution.getNextDouble();
		
		if(getSimObj().getNext().get(0).getClass() == SmoInfiniteGeneric.class) {
			SmoInfiniteGeneric smo = (SmoInfiniteGeneric)getSimObj().getNext().get(0);
			if(smo.isWolne()) {
				new FinishWaitOnSemaphoreEvent(getSimObj(), time, notification);
			}
			else {
				new WaitOnSemaphoreEvent(getSimObj(), time, notification);
			}
		}
		else {
			getSimObj().putToNexts(notification);
		}
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
