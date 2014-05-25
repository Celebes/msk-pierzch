package pl.edu.wat.msk.events;

import pl.edu.wat.msk.elements.Destination;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class DesposeNotificationEvent 
		extends BasicSimEvent<Destination, Object> {
	
	public DesposeNotificationEvent(Destination entity)
			throws SimControlException {
		super(entity);
	}

	@Override
	protected void stateChange() throws SimControlException {
		// TODO Auto-generated method stub
		
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
