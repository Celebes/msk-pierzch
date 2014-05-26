package pl.edu.wat.msk.events;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;
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
		this.generator = new SimGenerator();
	}
	
	public ThrowNotificationEvent(Source parent) throws SimControlException {
		super(parent);
		
		this.parent = parent;
		this.generator = new SimGenerator();
	}

	@Override
	protected void stateChange() throws SimControlException {
		/*parent = getSimObj();
		
		Notification n = new Notification(simTime());
		parent.putToNexts(n);
		
		System.out.println(simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": źródło: Dodano nowe zgl. nr: " + n.getId());
		
		//nowe zgłoszenie, po wygenerowanym opóźnieniu
		double deley = parent.getGenerateDistribution().getNextDouble();
		parent.durningNotificationTimeMV.setValue(deley);
		parent.setThrowNotification(new ThrowNotificationEvent(parent, deley));*/
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
