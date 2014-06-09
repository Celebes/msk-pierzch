package pl.edu.wat.msk.smo_generic;

import java.util.LinkedList;
import java.util.List;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.events.RozpocznijObslugeInfiniteGeneric;
import pl.edu.wat.msk.events.ZakonczObslugeInfiniteGeneric;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;

public class SmoInfiniteGeneric extends HavePrevNext {
	
	private boolean wolne = true;
	public RozpocznijObslugeInfiniteGeneric rozpocznijObsluge;
    public ZakonczObslugeInfiniteGeneric zakonczObsluge;
    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVutraconeZgl;
	
	// konstruktor dla nieskonczonej kolejki
	public SmoInfiniteGeneric(String id) {
		this.id = id;
		this.kolejka = new LinkedList <ZgloszenieGeneric>();
		MVczasy_obslugi = new MonitoredVar();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVutraconeZgl = new MonitoredVar();
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {
		// dodaj nowo otrzymane zgloszenie do kolejki
		int wynikDodawania = this.dodaj(zgl);

		// jesli jest zgloszenie i gniazdo jest wolne to uruchom obsluge
		if (wynikDodawania == 1 && this.isWolne()) {
        	try {
				this.rozpocznijObsluge = new RozpocznijObslugeInfiniteGeneric(this);
			} catch (SimControlException e) {
				e.printStackTrace();
			}
        }
	}

	public LinkedList<ZgloszenieGeneric> getKolejka() {
		return kolejka;
	}

	public void setKolejka(LinkedList<ZgloszenieGeneric> kolejka) {
		this.kolejka = kolejka;
	}

	public boolean isWolne() {
		return wolne;
	}

	public void setWolne(boolean wolne) {
		this.wolne = wolne;
	}

	public RozpocznijObslugeInfiniteGeneric getRozpocznijObsluge() {
		return rozpocznijObsluge;
	}

	public void setRozpocznijObsluge(RozpocznijObslugeInfiniteGeneric rozpocznijObsluge) {
		this.rozpocznijObsluge = rozpocznijObsluge;
	}

	public ZakonczObslugeInfiniteGeneric getZakonczObsluge() {
		return zakonczObsluge;
	}

	public void setZakonczObsluge(ZakonczObslugeInfiniteGeneric zakonczObsluge) {
		this.zakonczObsluge = zakonczObsluge;
	}

	public MonitoredVar getMVczasy_obslugi() {
		return MVczasy_obslugi;
	}

	public void setMVczasy_obslugi(MonitoredVar mVczasy_obslugi) {
		MVczasy_obslugi = mVczasy_obslugi;
	}

	public MonitoredVar getMVczasy_oczekiwania() {
		return MVczasy_oczekiwania;
	}

	public void setMVczasy_oczekiwania(MonitoredVar mVczasy_oczekiwania) {
		MVczasy_oczekiwania = mVczasy_oczekiwania;
	}

	public MonitoredVar getMVdlKolejki() {
		return MVdlKolejki;
	}

	public void setMVdlKolejki(MonitoredVar mVdlKolejki) {
		MVdlKolejki = mVdlKolejki;
	}

	public MonitoredVar getMVutraconeZgl() {
		return MVutraconeZgl;
	}

	public void setMVutraconeZgl(MonitoredVar mVutraconeZgl) {
		MVutraconeZgl = mVutraconeZgl;
	}

	@Override
	public void setNext(List<IModelComponent> next) {
		this.nexts = next;
	}

}
