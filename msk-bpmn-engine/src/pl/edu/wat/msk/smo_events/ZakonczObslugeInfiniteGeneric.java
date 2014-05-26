package pl.edu.wat.msk.smo_events;

import pl.edu.wat.msk.smo_generic.SmoGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import smo.RozpocznijObsluge;
import smo.RozpocznijObslugeBis;
import smo.Smo;
import smo.ZakonczObsluge;
import smo.Zgloszenie;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;

public class ZakonczObslugeInfiniteGeneric extends BasicSimEvent<SmoGeneric, ZgloszenieGeneric>
{
    public ZakonczObslugeInfiniteGeneric(SmoGeneric parent, double delay, ZgloszenieGeneric zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }

    public ZakonczObslugeInfiniteGeneric(SmoGeneric parent, SimEventSemaphore semafor, ZgloszenieGeneric zgl) throws SimControlException
    {
    	super(parent, semafor, zgl);
    }
    
	@Override
	protected void onInterruption() throws SimControlException {
		// TODO
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
	    SmoInfiniteGeneric parent = getSimObj();
	    
	    if(parent.getMaxDlugoscKolejki() == null || parent.dodaj(transitionParams)) {
	    	// gdy obsluga sie zakonczyla to przeslij zgloszenie dalej
			parent.putToNexts(transitionParams);
			
			// zwolnij gniazdo
			parent.setWolne(true);
			
			System.out.println(simTime()+": Koniec obslugi zgl. nr: " + transitionParams.getTenNr() + " w SMO " + parent.getId());
			
			parent.getMVczasy_obslugi().setValue(simTime() - transitionParams.getCzasOdniesienia());
			
			// zaplanuj dalsza obsluge
			if(parent.getKolejka().size() > 0) {
				parent.rozpocznijObsluge = new RozpocznijObslugeInfiniteGeneric(parent);
			}
	    }
	    else {
	    	System.out.println(simTime()+": Oczekiwanie na semaforze - zgl. nr: " + transitionParams.getTenNr());
        	parent.zakonczObsluge = new ZakonczObslugeInfiniteGeneric(parent, parent.getSemafor(), transitionParams);
	    }
	}
}