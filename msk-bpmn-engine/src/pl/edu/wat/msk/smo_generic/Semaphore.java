package pl.edu.wat.msk.smo_generic;

import java.util.ArrayList;

import pl.edu.wat.msk.elements.HavePrevNext;

/**
 * Semafor
 * 
 * @since 07.06.2014
 * @author Mariusz Kielan
 *
 */
public class Semaphore extends HavePrevNext {
	private ArrayList<ZgloszenieGeneric> waiting = new ArrayList<>();
	
	
	
	public Semaphore() {
		
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl) {
		// TODO Auto-generated method stub
		super.processing(zgl);
	}
}
