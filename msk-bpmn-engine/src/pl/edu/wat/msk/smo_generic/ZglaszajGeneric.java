package pl.edu.wat.msk.smo_generic;

import pl.edu.wat.msk.elements.IModelComponent;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

/*
 * Generuje kolejne zgloszenia bazujac na rozkladzie
 */

public class ZglaszajGeneric extends BasicSimEvent<OtoczenieGeneric, Object> {

    private OtoczenieGeneric parent;
	
    public ZglaszajGeneric(OtoczenieGeneric parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    }

    public ZglaszajGeneric(OtoczenieGeneric parent) throws SimControlException
    {
    	super(parent);
    }

	@Override
	protected void stateChange() throws SimControlException {
		parent = getSimObj();
        ZgloszenieGeneric zgl = new ZgloszenieGeneric(simTime(), null);
                
        // wyslij utworzone zgloszenie do wszystkich nastepnikow parent
        parent.putToNexts(zgl);
        System.out.println(simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Otoczenie[" + this.parent.getId() + "] : Dodano nowe zgl. nr: " + zgl.getTenNr());
        
        // Wygeneruj czas do kolejnego zgloszenia
        double odstep = parent.getNextDouble();
        parent.getMVczasy_miedzy_zgl().setValue(odstep);
        parent.setZglaszaj(new ZglaszajGeneric(parent, odstep));
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
