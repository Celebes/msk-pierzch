package pl.edu.wat.msk.elements;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;

/**
 * Komponent budowanego modelu.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.1
 */
public abstract class ModelComponent extends BasicSimObj implements IModelComponent {
	/**
	 * Semafor dla SMO
	 */
	private static final SimEventSemaphore SEMAFOR = new SimEventSemaphore("Semafor dla SMO"); 
	
	/**
	 * Nazwa komponentu określona przez użytkownika.
	 */
	private String name;

	/**
	 * Opis komponentu określony przez użytkownika.
	 */
	private String description;
	
	private Integer maxDlugoscKolejki;
	
	protected LinkedList<ZgloszenieGeneric> kolejka;
	public MonitoredVar MVdlKolejki;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl) {
	}

	public List<ZgloszenieGeneric> getListaZgloszen() {
		return kolejka;
	}

	public void setListaZgloszen(List<ZgloszenieGeneric> listaZgloszen) {
		this.kolejka = new LinkedList<ZgloszenieGeneric>(listaZgloszen);
	}
	
	// dodaje zgloszenie do kolejki
	// zwrocenie 0 oznacza false (to takie obejscie, bo dodaj() w SmoBis zwracalo boolean
	public boolean dodaj(ZgloszenieGeneric zgl) {
		if(maxDlugoscKolejki == null || maxDlugoscKolejki < kolejka.size()) {
			kolejka.add(zgl);
			MVdlKolejki.setValue(kolejka.size());
			
			return true;
		}
		return false;
	}
	
	// pobiera zgloszenie z kolejki
	public ZgloszenieGeneric usun() {
		ZgloszenieGeneric zgl = (ZgloszenieGeneric) kolejka.removeFirst();
        MVdlKolejki.setValue(kolejka.size());
        return zgl;
	}
	
	// pobiera wskazane zgloszenie z kolejki
	public boolean usunWskazany(ZgloszenieGeneric zgl)
    {
    	Boolean b= kolejka.remove(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return b;
    }

	public Integer getMaxDlugoscKolejki() {
		return maxDlugoscKolejki;
	}

	public void setMaxDlugoscKolejki(Integer maxDlugoscKolejki) {
		this.maxDlugoscKolejki = maxDlugoscKolejki;
	}
	
	public static SimEventSemaphore getSemafor() {
		return SEMAFOR;
	}
}