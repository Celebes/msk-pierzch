package pl.edu.wat.msk.elements;

import java.util.LinkedList;
import java.util.List;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

/**
 * Komponent budowanego modelu.
 * 
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.1
 */
public abstract class ModelComponent extends BasicSimObj implements IModelComponent {

	/**
	 * Nazwa komponentu określona przez użytkownika.
	 */
	private String name;

	/**
	 * Opis komponentu określony przez użytkownika.
	 */
	private String description;
	
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
		this.kolejka = kolejka;
	}
	
	// dodaje zgloszenie do kolejki
	// zwrocenie 0 oznacza false (to takie obejscie, bo dodaj() w SmoBis zwracalo boolean
	public int dodaj(ZgloszenieGeneric zgl) {
		kolejka.add(zgl);
		MVdlKolejki.setValue(kolejka.size());
		return kolejka.size();
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
}