package pl.edu.wat.msk.elements;

import java.util.Vector;

import dissimlab.monitors.MonitoredVar;
import pl.edu.wat.msk.Notification;
import pl.edu.wat.msk.distributions.IDistribution;

/**
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 *
 */
public class Source extends HavePrevNext {
	
	/** 
	 *  Rozkład przerw pomiedzy generowaniem zgłoszeń
	 */
	public IDistribution breaksDistribution;
	
	/** 
	 *  Rozkład liczby generowanych zgłoszeń jednocześnie
	 */
	public IDistribution generateDistribution;
	
	/** 
	 *  Czas generowania zgłoszeń ( w sekundach ). 
	 *  Jeśli 0 sek to decyduje liczba zgłoszeń do wygenerowania.
	 */
	public int generateTime;
	
	/**
	 * Czasy między zgłoszeniami
	 */
	public MonitoredVar durningNotificationTimeMV;
	
	public Source() {
		super();
		durningNotificationTimeMV = new MonitoredVar();
	}
	
	public Source(IModelComponent next, 
			IDistribution breaksDistribution, 
			IDistribution generateDistribution) {
		
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
	public Source(IModelComponent next) {
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
	
	/**
	 *  Generowanie zgłoszeń, wykorzystując zadane rozkłady.
	 * 
	 * @param notification - atrybut pomijalny.
	 */
	@Override
	public void processing(Notification notification) {
		// TODO Auto-generated method stub
		
	}

}