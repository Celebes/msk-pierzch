package pl.edu.wat.msk.elements;

import java.util.Vector;

import pl.edu.wat.msk.Notification;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import dissimlab.simcore.SimEventSemaphore;

/**
 * 
 * @since 24.05.2014, 19:50
 * Generated by AgroUML
 *
 */
public class Semaphore extends HavePrevNext implements IHaveNext {

	private SimEventSemaphore semaphore;
	
	public boolean isOpen() {
	return false;
	}

	@Override
	public Vector validate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processing(ZgloszenieGeneric zgl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putToNexts(ZgloszenieGeneric zgl) {
		// TODO Auto-generated method stub
		
	}

}