package pl.edu.wat.msk.elements;

import java.util.Vector;

import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;

import pl.edu.wat.msk.Notification;
import pl.edu.wat.msk.distributions.IDistribution;
import pl.edu.wat.msk.events.ThrowNotificationEvent;

/**
 * źródło generujące zgłoszenia dla systemu.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.2
 */
public class Source extends HavePrevNext {
	private ThrowNotificationEvent throwNotification;

	/** 
	 *  Rozkład przerw pomiedzy generowaniem zgłoszeń
	 */
	private IDistribution breaksDistribution;
	
	/** 
	 *  Rozkład liczby generowanych zgłoszeń jednocześnie
	 */
	private IDistribution generateDistribution;
	
	/** 
	 *  Czas generowania zgłoszeń ( w sekundach ). 
	 *  Jeśli 0 sek to decyduje liczba zgłoszeń do wygenerowania.
	 */
	public int generateTime;
	
	/**
	 * Czasy między zgłoszeniami
	 */
	public MonitoredVar durningNotificationTimeMV;
	
	public Source() throws SimControlException {
		super();
		durningNotificationTimeMV = new MonitoredVar();
		throwNotification = new ThrowNotificationEvent(this);
	}
	
	public Source(IModelComponent next, 
			IDistribution breaksDistribution, 
			IDistribution generateDistribution)  throws SimControlException {
		
		this();
		nexts.add(next);
		
		this.breaksDistribution = breaksDistribution;
		this.generateDistribution = generateDistribution;
	}
	
	/** 
	 *  Konstruktor wykorzystywany do zastosowania obiektu wewnątrz złożonej aktywności. 
	 *  Wówczas obiekt nie generuje zgłoszeń, a staje się pośrednikiem prekazującym dalej 
	 *  zgłoszenie, które do niego dotarło.
	 */
	public Source(IModelComponent next)  throws SimControlException {
		this();
		nexts.add(next);
	}
	
	/** 
	 *  Metoda wywoływana przez aktywność złożoną. 
	 *  W celu przekazania zgłoszenia przez źródło.
	 */
	public void handOver(Notification notification) {
	}
	
	@Override
	public Vector<ValidationMessage> validate() {
		Vector<ValidationMessage> msgs = new Vector<>();
		
		if(this.nexts.isEmpty()) {
			msgs.add(
				new ValidationMessage(
					"Brak następników dla źródła", 
					"Każde źródło powinno posiadać przynajmniej jeden następnik", 
					this
					)
			);
		}
		
		return msgs;
	}

	public IDistribution getBreaksDistribution() {
		return breaksDistribution;
	}

	public IDistribution getGenerateDistribution() {
		return generateDistribution;
	}

	public void setBreaksDistribution(IDistribution breaksDistribution) {
		this.breaksDistribution = breaksDistribution;
	}

	public void setGenerateDistribution(IDistribution generateDistribution) {
		this.generateDistribution = generateDistribution;
	}

	public ThrowNotificationEvent getThrowNotification() {
		return throwNotification;
	}

	public void setThrowNotification(ThrowNotificationEvent throwNotification) {
		this.throwNotification = throwNotification;
	}
}