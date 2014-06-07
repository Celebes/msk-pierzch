/**
 * Łukasz Kotowski
 */
package pl.edu.wat.msk.smo_generic;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.elements.ValidationMessage;

public class LogicGateGeneric extends HavePrevNext {
	private String id;
	private String rodzaj;

	public LogicGateGeneric(String id, String rodzaj) {
		this.id = id;
		this.rodzaj = rodzaj;
	}

	@Override
	public void processing(ZgloszenieGeneric zgl) {
		Random generujPrzejscie = new Random();
		List<IModelComponent> kolejneKomponenty = getNext();
		int liczbaWyjscBramki = getNext().size();
		int wylosowanePrzejscie = 1 + generujPrzejscie
				.nextInt(liczbaWyjscBramki);
		getNext().get(wylosowanePrzejscie).processing(zgl);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRodzaj() {
		return rodzaj;
	}

	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}

	@Override
	public Vector<ValidationMessage> validate() {
	    // TODO Auto-generated method stub
	    return null;
	}

}