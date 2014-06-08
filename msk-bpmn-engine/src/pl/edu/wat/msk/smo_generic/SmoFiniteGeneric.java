package pl.edu.wat.msk.smo_generic;

import java.util.LinkedList;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.events.RozpocznijObslugeFiniteGeneric;
import pl.edu.wat.msk.events.ZakonczObslugeFiniteGeneric;
import dissimlab.monitors.MonitoredVar;

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

}
