package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class StartNiecierpliwosciGeneric extends BasicSimEvent<ZgloszenieGeneric, Object>
{
    private SimGenerator generator;
    private ZgloszenieGeneric parent;

    public StartNiecierpliwosciGeneric(ZgloszenieGeneric parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
        this.parent = parent;
    }

    public StartNiecierpliwosciGeneric(ZgloszenieGeneric parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
        this.parent = parent;
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
        System.out.println(simTime()+": Poczatek niecierpliwosci zgl. nr: " + parent.getTenNr());
        double odstep = generator.normal(15.0, 1.0);
        parent.koniecNiecierpliwosci = new KoniecNiecierpliwosciGeneric(parent, odstep);
	}
}