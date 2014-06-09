package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.SmoFiniteGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import smo.ZakonczObsluge;
import smo.Zgloszenie;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpocznijObslugeFiniteGeneric extends BasicSimEvent<SmoFiniteGeneric, ZgloszenieGeneric> {
    private SimGenerator generator;

    public RozpocznijObslugeFiniteGeneric(SmoFiniteGeneric parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
    }

    public RozpocznijObslugeFiniteGeneric(SmoFiniteGeneric parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
    }

	@Override
	protected void stateChange() throws SimControlException {
		SmoFiniteGeneric parent = getSimObj();
		
		if (parent.getListaZgloszen().size() > 0) {
        	// Zablokuj gniazdo
        	parent.setWolne(false);
        	// Pobierz zgłoszenie
        	ZgloszenieGeneric zgl = parent.usun();

        	// Przerwanie niecierpliwosci
        	if(zgl.koniecNiecierpliwosci != null)
        		zgl.koniecNiecierpliwosci.interrupt();
        	
        	// Wygeneruj czas obsługi
        	double czasObslugi = generator.normal(9.0, 1.0);
            // Zapamiętaj dane monitorowane
            parent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasOdniesienia());
            zgl.setCzasOdniesienia(simTime());
            System.out.println(simTime()+": Początek obsługi zgl. nr: " + zgl.getTenNr());
        	// Zaplanuj koniec obsługi
        	parent.zakonczObsluge = new ZakonczObslugeFiniteGeneric(parent, czasObslugi, zgl); 
		}		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}
}
