package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import smo.RozpocznijObsluge;
import smo.RozpocznijObslugeBis;
import smo.Smo;
import smo.ZakonczObsluge;
import smo.Zgloszenie;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;

public class ZakonczObslugeInfiniteGeneric extends BasicSimEvent<SmoInfiniteGeneric, ZgloszenieGeneric>
{
    private SmoInfiniteGeneric smoParent;

    public ZakonczObslugeInfiniteGeneric(SmoInfiniteGeneric parent, double delay, ZgloszenieGeneric zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
        this.smoParent = parent;
    }

    public ZakonczObslugeInfiniteGeneric(SmoInfiniteGeneric parent, SimEventSemaphore semafor, ZgloszenieGeneric zgl) throws SimControlException
    {
    	super(parent, semafor, zgl);
        this.smoParent = parent;
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
		// gdy obsluga sie zakonczyla to przeslij zgloszenie dalej
		smoParent.putToNexts(transitionParams);
		
		// zwolnij gniazdo
		smoParent.setWolne(true);
		
		System.out.println(simTime()+": Koniec obslugi zgl. nr: " + transitionParams.getTenNr() + " w SMO " + smoParent.getId());
		
		smoParent.getMVczasy_obslugi().setValue(simTime() - transitionParams.getCzasOdniesienia());
		
		// zaplanuj dalsza obsluge
		if(smoParent.getKolejka().size() > 0) {
			smoParent.rozpocznijObsluge = new RozpocznijObslugeInfiniteGeneric(smoParent);
		}
	}
}