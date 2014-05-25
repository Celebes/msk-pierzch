package pl.edu.wat.msk.events;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import pl.edu.wat.msk.Notification;
import pl.edu.wat.msk.elements.Source;;

/**
 * Zdarzenie generowania zgłoszeń.
 * 
 * @author Mariusz Kielan
 * @since 25.05.2014, 10:40
 * @version 0.0.1
 *
 */
public class ThrowNotificationEvent 
	extends BasicSimEvent<Source, Object> {

	private SimGenerator generator;
	private Source parent;
	
	public ThrowNotificationEvent(Source parent, double delay) throws SimControlException {
		super(parent, delay);
		this.parent = parent;
	}
	
	public ThrowNotificationEvent(Source parent) throws SimControlException {
		super(parent);
		this.parent = parent;
	}

	@Override
	protected void stateChange() throws SimControlException {
		parent = getSimObj();
		
		Notification n = new Notification(simTime());
		
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

}
