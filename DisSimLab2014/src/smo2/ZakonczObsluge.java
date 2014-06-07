package smo2;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Aktywno�� gniazda obs�ugi. Realizuje obs�ug� przez losowy czas obiekt�w - zg�osze�.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimEventSemaphore;
import dissimlab.simcore.SimControlException;

public class ZakonczObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;

    public ZakonczObsluge(Smo parent, double delay, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
        this.smoParent = parent;
    }

    public ZakonczObsluge(Smo parent, SimEventSemaphore semafor, Zgloszenie zgl) throws SimControlException
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
		// Odblokuj gniazdo
		smoParent.setWolne(true);
		//AA-test-AA System.out.println(simTime()+": SMO1-Koniec obs�ugi zgl. nr: " + transitionParams.getTenNr());
		// Zaplanuj dalsza obs�uge
        if (smoParent.liczbaZgl() > 0)
        {
        	smoParent.rozpocznijObsluge = new RozpocznijObsluge(smoParent);        	
        }		
	}
}