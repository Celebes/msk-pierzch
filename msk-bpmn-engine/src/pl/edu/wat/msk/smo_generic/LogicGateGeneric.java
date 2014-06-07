/**
 * Łukasz Kotowski
 */
package pl.edu.wat.msk.smo_generic;

import java.util.List;
import java.util.Random;

import pl.edu.wat.msk.distributions.AbstractDistribution;
import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.utils.SelectDistributionUtil;
import pl.edu.wat.wcy.mtsk.xml_elements.Param;

public class LogicGateGeneric extends HavePrevNext {
	private String rodzaj;
	private AbstractDistribution distribution;
	private List<Param> params;

	public LogicGateGeneric(String id, String rodzaj, String distributionName, List<Param> params) {
		setId( id );
		this.rodzaj = rodzaj;
		this.setDistribution(SelectDistributionUtil.getDistributionByName(distributionName, params));
		//Być może nie będzie to poniżej używane
		this.params = params;
	}

	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {
		Random generujPrzejscie = new Random();
		List<IModelComponent> kolejneKomponenty = getNext();
		int liczbaWyjscBramki = getNext().size();
		int wylosowanePrzejscie = 1 + generujPrzejscie
				.nextInt(liczbaWyjscBramki);
		getNext().get(wylosowanePrzejscie).processing(zgl, getId());

	}

	public String getRodzaj() {
		return rodzaj;
	}

	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}

	public AbstractDistribution getDistribution() {
	    return distribution;
	}

	public void setDistribution(AbstractDistribution distribution) {
	    this.distribution = distribution;
	}

	public List<Param> getParams() {
	    return params;
	}

	public void setParams(List<Param> params) {
	    this.params = params;
	}

}