package pl.edu.wat.msk.smo_generic;

import java.util.LinkedList;
import java.util.List;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.smo_events.RozpocznijObslugeInfiniteGeneric;
import pl.edu.wat.msk.smo_events.ZakonczObslugeInfiniteGeneric;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;

public class SmoGeneric extends HavePrevNext {
	
	private String id;
	//private LinkedList <ZgloszenieGeneric> kolejka;
	private boolean wolne = true;
	public RozpocznijObslugeInfiniteGeneric rozpocznijObsluge;
    public ZakonczObslugeInfiniteGeneric zakonczObsluge;
    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    
    public MonitoredVar MVutraconeZgl;
	
	// tylko dla skonczonych kolejek
    private boolean kolejkaSkonczona = false;
	private SimEventSemaphore semafor;
	
	// konstruktor dla ograniczonej kolejki
	public SmoGeneric(String id, int maxDlKolejki) throws SimControlException {
		this(id);
		this.kolejkaSkonczona = true;
		this.setMaxDlugoscKolejki(maxDlKolejki);
		this.semafor = new SimEventSemaphore("Semafor dla SMO");
	}
	
	// konstruktor dla nieskonczonej kolejki
	public SmoGeneric(String id) {
		this.id = id;
		this.kolejka = new LinkedList <ZgloszenieGeneric>();
		MVczasy_obslugi = new MonitoredVar();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVutraconeZgl = new MonitoredVar();
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl) {
		// dodaj nowo otrzymane zgloszenie do kolejki
		//int wynikDodawania = this.dodaj(zgl);
		
		// jesli mamy kolejke skonczona i nie udalo sie dodac, tzn. ze trzeba dac do semafora
		/*if(kolejkaSkonczona && wynikDodawania == (-1)) {
			// trzeba dodac do semafora, bo brakuje miejsca
			try {
				this.zakonczObsluge = new ZakonczObslugeInfiniteGeneric(this, this.semafor, zgl);
				System.out.println(simTime()+": Oczekiwanie na semaforze w SMO" + this.getId() + " - zgl. nr: " + zgl.getTenNr());
			} catch (SimControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		// jesli mamy kolejke skonczona to sprobuj przeniesc cos z semafora do kolejki
		
		// jesli jest zgloszenie i gniazdo jest wolne to uruchom obsluge
		if (this.kolejka.size() == 1 && this.isWolne()) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setNext(List<IModelComponent> next) {
		this.nexts = next;
	}

	public boolean isKolejkaSkonczona() {
		return kolejkaSkonczona;
	}

	public void setKolejkaSkonczona(boolean kolejkaSkonczona) {
		this.kolejkaSkonczona = kolejkaSkonczona;
	}

	public SimEventSemaphore getSemafor() {
		return semafor;
	}

	public void setSemafor(SimEventSemaphore semafor) {
		this.semafor = semafor;
	}

}
