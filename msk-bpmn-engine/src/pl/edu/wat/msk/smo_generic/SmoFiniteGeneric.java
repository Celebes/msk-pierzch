package pl.edu.wat.msk.smo_generic;

import java.util.LinkedList;
import java.util.List;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.events.RozpocznijObslugeFiniteGeneric;
import pl.edu.wat.msk.events.ZakonczObslugeFiniteGeneric;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;

/*
 * Kolejka skonczona
 */

public class SmoFiniteGeneric extends HavePrevNext {

	private boolean wolne = true;
	public RozpocznijObslugeFiniteGeneric rozpocznijObsluge;
	public ZakonczObslugeFiniteGeneric zakonczObsluge;
	public MonitoredVar MVczasy_obslugi;
	public MonitoredVar MVczasy_oczekiwania;
	public MonitoredVar MVutraconeZgl;

	private int maxDlKolejki;

	// konstruktor dla skonczonej kolejki
	public SmoFiniteGeneric(String id, int maxDlKolejki) {
		this.id = id;
		this.maxDlKolejki = maxDlKolejki;

		this.kolejka = new LinkedList<ZgloszenieGeneric>();
		MVczasy_obslugi = new MonitoredVar();
		MVczasy_oczekiwania = new MonitoredVar();
		MVdlKolejki = new MonitoredVar();
		MVutraconeZgl = new MonitoredVar();
	}

	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {
		if(kolejka.size() < maxDlKolejki) {
			// dodaj nowo otrzymane zgloszenie do kolejki
			int wynikDodawania = this.dodaj(zgl);

			// jesli jest zgloszenie i gniazdo jest wolne to uruchom obsluge
			if (wynikDodawania == 1 && this.isWolne()) {
	        	try {
					this.rozpocznijObsluge = new RozpocznijObslugeFiniteGeneric(this);
				} catch (SimControlException e) {
					e.printStackTrace();
				}
	        }
		}
		else {

			// wyjście zgłoszenia (nie może być przyjęte)
			System.out.println("Zgloszenie nr " + zgl.getTenNr() + " opuszcza system, brak miejsca w kolejce SMO " + this.getId());
			SimSystem.getInstance().failure(zgl);
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

	public RozpocznijObslugeFiniteGeneric getRozpocznijObsluge() {
		return rozpocznijObsluge;
	}

	public void setRozpocznijObsluge(RozpocznijObslugeFiniteGeneric rozpocznijObsluge) {
		this.rozpocznijObsluge = rozpocznijObsluge;
	}

	public ZakonczObslugeFiniteGeneric getZakonczObsluge() {
		return zakonczObsluge;
	}

	public void setZakonczObsluge(ZakonczObslugeFiniteGeneric zakonczObsluge) {
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

	public int getMaxDlKolejki() {
		return maxDlKolejki;
	}

	public void setMaxDlKolejki(int maxDlKolejki) {
		this.maxDlKolejki = maxDlKolejki;
	}
}
