/**
 * Ĺ�ukasz Kotowski
 */
package pl.edu.wat.msk.smo_generic;

import java.util.List;
import java.util.Random;

import pl.edu.wat.msk.distributions.IDistribution;
import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;

public class LogicGateGeneric extends HavePrevNext {
	private String id;
	private String rodzaj;
	private IDistribution rozklad;

	public LogicGateGeneric(String id, String rodzaj) {
		this.id = id;
		this.rodzaj = rodzaj;
	}

	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {

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

}