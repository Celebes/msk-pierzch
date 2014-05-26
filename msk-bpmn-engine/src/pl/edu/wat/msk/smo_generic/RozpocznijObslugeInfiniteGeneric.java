package pl.edu.wat.msk.smo_generic;

import smo.Smo;
import smo.ZakonczObsluge;
import smo.Zgloszenie;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpocznijObslugeInfiniteGeneric extends BasicSimEvent<SmoInfiniteGeneric, ZgloszenieGeneric> {
	private SmoInfiniteGeneric smoParent;
    private SimGenerator generator;

    public RozpocznijObslugeInfiniteGeneric(SmoInfiniteGeneric parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
        this.smoParent = parent;
    }

    public RozpocznijObslugeInfiniteGeneric(SmoInfiniteGeneric parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
        this.smoParent = parent;
    }
    
	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
        if (smoParent.getKolejka().size() > 0)
        {
        	// Zablokuj gniazdo
        	smoParent.setWolne(false);
        	// Pobierz zgloszenie
        	ZgloszenieGeneric zgl = smoParent.usun();
        	// Przerwanie niecierpliwosci
        	zgl.koniecNiecierpliwosci.interrupt();
        	// Wygeneruj czas obslugi
        	double czasObslugi = generator.normal(9.0, 1.0);
            // Zapamietaj dane monitorowane
            smoParent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasOdniesienia());
            zgl.setCzasOdniesienia(simTime());
            System.out.println(simTime()+": Poczatek obslugi zgl. nr: " + zgl.getTenNr() + " w SMO " + smoParent.getId());
        	// Zaplanuj koniec obslugi
        	smoParent.zakonczObsluge = new ZakonczObslugeInfiniteGeneric(smoParent, czasObslugi, zgl);        	
        }
		
	}
}
