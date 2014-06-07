package pl.edu.wat.msk.events;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class KoniecNiecierpliwosciGeneric extends BasicSimEvent<ZgloszenieGeneric, Object>
{
    private SimGenerator generator;
    private ZgloszenieGeneric parent;

    public KoniecNiecierpliwosciGeneric(ZgloszenieGeneric parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
        this.parent = parent;
    }

    public KoniecNiecierpliwosciGeneric(ZgloszenieGeneric parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
        this.parent = parent;
    }
    
	@Override
	protected void onInterruption() throws SimControlException {
        System.out.println(simTime()+": Przerwanie niecierpliwo�ci zgl. nr: " + parent.getTenNr());
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
		System.out.println(simTime()+": Koniec niecierpliwosci zgl. nr: " + parent.getTenNr());
		
		//if(parent.aktualnieObslugujacyKomponent.)
		
		
        /*System.out.println(simTime()+": Koniec niecierpliwosci zgl. nr: " + parent.getTenNr());
        if (parent.smo.usunWskazany(parent)){
            System.out.println(simTime()+": Usuni�to z kolejki zgl. nr: " + parent.getTenNr());
            double lutrac = parent.getS
            double lutrac = parent.getSmo().getMVutraconeZgl().getValue();
            parent.smo.MVutraconeZgl.setValue(lutrac++);
        }
        else
            System.out.println(simTime()+": Problem z usuni�ciem z kolejki zgl. nr: " + parent.getTenNr());*/
	}
}