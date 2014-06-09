package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;

public class ZakonczObslugeInfiniteGeneric extends BasicSimEvent<SmoInfiniteGeneric, ZgloszenieGeneric>
{
    public ZakonczObslugeInfiniteGeneric(SmoInfiniteGeneric parent, double delay, ZgloszenieGeneric zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }

    public ZakonczObslugeInfiniteGeneric(SmoInfiniteGeneric parent, SimEventSemaphore semafor, ZgloszenieGeneric zgl) throws SimControlException
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
}