package pl.edu.wat.msk.smo_generic;

import java.util.List;

import pl.edu.wat.msk.distributions.AbstractDistribution;
import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.events.ZglaszajGeneric;
import pl.edu.wat.msk.utils.SelectDistributionUtil;
import pl.edu.wat.wcy.mtsk.xml_elements.Param;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;

/**
 * 
 * Generator, ktory zawiera w sobie obiekt Zglaszaj generujacy kolejne zgloszenia
 */

public class OtoczenieGeneric extends HavePrevNext {
	private ZglaszajGeneric zglaszaj;
	private AbstractDistribution distribution;
	private MonitoredVar MVczasy_miedzy_zgl;

	public OtoczenieGeneric(String id, String type, List<Param> parametry) throws SimControlException {
		this.id = id;
		distribution = SelectDistributionUtil.getDistributionByName(type, parametry);
		zglaszaj = new ZglaszajGeneric(this, 0.0);
		MVczasy_miedzy_zgl = new MonitoredVar();
	}

	public double getNextDouble() {
		return distribution.getNextDouble();
	}

	public ZglaszajGeneric getZglaszaj() {
		return zglaszaj;
	}

	public void setZglaszaj(ZglaszajGeneric zglaszaj) {
		this.zglaszaj = zglaszaj;
	}

	public AbstractDistribution getDistribution() {
		return distribution;
	}

	public void setDistribution(AbstractDistribution distribution) {
		this.distribution = distribution;
	}

	public MonitoredVar getMVczasy_miedzy_zgl() {
		return MVczasy_miedzy_zgl;
	}

	public void setMVczasy_miedzy_zgl(MonitoredVar mVczasy_miedzy_zgl) {
		MVczasy_miedzy_zgl = mVczasy_miedzy_zgl;
	}

	@Override
	public void setNext(List<IModelComponent> next) {
		super.setNext(next);
	}

}
