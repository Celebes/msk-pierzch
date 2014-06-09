package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpocznijObslugeInfiniteGeneric extends BasicSimEvent<SmoInfiniteGeneric, ZgloszenieGeneric> {
    private SimGenerator generator;

    public RozpocznijObslugeInfiniteGeneric(SmoInfiniteGeneric parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
    }

    public RozpocznijObslugeInfiniteGeneric(SmoInfiniteGeneric parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
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
		SmoInfiniteGeneric parent = getSimObj();
		
        if (parent.getKolejka().size() > 0)
        {
        	// Zablokuj gniazdo
        	parent.setWolne(false);
        	// Pobierz zgloszenie
        	ZgloszenieGeneric zgl = parent.usun();
        	// Przerwanie niecierpliwosci
        	// zgl.koniecNiecierpliwosci.interrupt();
        	// Wygeneruj czas obslugi
        	double czasObslugi = generator.normal(9.0, 1.0);
            // Zapamietaj dane monitorowane
            parent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasOdniesienia());
            zgl.setCzasOdniesienia(simTime());
            System.out.println(simTime()+": Poczatek obslugi zgl. nr: " + zgl.getTenNr() + " w SMO " + parent.getId());
        	// Zaplanuj koniec obslugi
        	parent.zakonczObsluge = new ZakonczObslugeInfiniteGeneric(parent, czasObslugi, zgl);        	
        }
		
	}
}
