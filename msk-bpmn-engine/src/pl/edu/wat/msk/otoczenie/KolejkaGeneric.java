package pl.edu.wat.msk.otoczenie;

import java.util.LinkedList;

import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import smo.RozpocznijObslugeBis;
import smo.ZakonczObslugeBis;
import smo.Zgloszenie;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;

public class KolejkaGeneric extends BasicSimObj {

	private LinkedList<Zgloszenie> kolejka;
	private boolean wolne = true;
	private int maxDlKolejki;
	private boolean isNieskonczona;
	SimEventSemaphore semafor;
	public RozpocznijObslugeBis rozpocznijObsluge;
	public ZakonczObslugeBis zakonczObsluge;
	// public OdblokujGniazdo odblokuj;
	//
	public MonitoredVar MVczasy_obslugi;
	public MonitoredVar MVczasy_oczekiwania;
	public MonitoredVar MVdlKolejki;

	public KolejkaGeneric(Symulacja.Czynnosc.Kolejka kolejka) {
		this.isNieskonczona = kolejka.isNieskonczona();
		this.maxDlKolejki = Integer.parseInt(kolejka.getMaxDlugoscKolejki());
		this.kolejka = new LinkedList<Zgloszenie>();
		// Deklaracja zmiennych monitorowanych
		MVczasy_obslugi = new MonitoredVar();
		MVczasy_oczekiwania = new MonitoredVar();
		MVdlKolejki = new MonitoredVar();
	}

	/**
	 * Dodanie klienta do kolejki
	 * 
	 * @param zgloszenie
	 * @return
	 */
	public boolean dodaj(Zgloszenie zgloszenie) {
		if (isNieskonczona) {
			kolejka.add(zgloszenie);
			MVdlKolejki.setValue(kolejka.size());
			return true;
		} else {
			if (liczbaZgl() < maxDlKolejki) {
				kolejka.add(zgloszenie);
				MVdlKolejki.setValue(kolejka.size());
				return true;
			}
			return false;
		}

	}

	/**
	 * Pobranie zgłoszenia z kolejki
	 * 
	 * @return
	 */
	public Zgloszenie usun() {
		Zgloszenie zgloszenie = (Zgloszenie) kolejka.removeFirst();
		MVdlKolejki.setValue(kolejka.size());
		return zgloszenie;
	}

	/**
	 * Pobranie konkretnego zgłoszenia z kolejki
	 * 
	 * @param zgloszenie
	 * @return
	 */
	public boolean usunWskazany(Zgloszenie zgloszenie) {
		Boolean isRemoved = kolejka.remove(zgloszenie);
		MVdlKolejki.setValue(kolejka.size());
		return isRemoved;
	}

	public int liczbaZgl() {
		return kolejka.size();
	}

	public boolean isWolne() {
		return wolne;
	}

	public void setWolne(boolean wolne) {
		this.wolne = wolne;
	}

	public SimEventSemaphore getSemafor() {
		return semafor;
	}

	public void setSemafor(SimEventSemaphore semafor) {
		this.semafor = semafor;
	}

	public int getMaxDlKolejki() {
		return maxDlKolejki;
	}

	public void setMaxDlKolejki(int maxDlKolejki) throws Exception {
		if (isNieskonczona) {
			throw new Exception(
					"Ta kolejka jest nieskończona! Nie można ogarniczyć jej długości!");
		} else {
			this.maxDlKolejki = maxDlKolejki;
		}

	}
}
