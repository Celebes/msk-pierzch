package pl.edu.wat.msk.smo_generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pl.edu.wat.msk.distributions.AbstractDistribution;
import pl.edu.wat.msk.distributions.Erlang;
import pl.edu.wat.msk.distributions.Normal;
import pl.edu.wat.msk.distributions.Uniform;
import pl.edu.wat.msk.elements.HaveNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.elements.ValidationMessage;
import pl.edu.wat.msk.events.ZglaszajGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;

/*
 * 
 * Generator, ktory zawiera w sobie obiekt Zglaszaj generujacy kolejne zgloszenia
 */

public class OtoczenieGeneric extends HaveNext {

	private String id;
	private ZglaszajGeneric zglaszaj;
	private AbstractDistribution distribution;
	private MonitoredVar MVczasy_miedzy_zgl;

	public OtoczenieGeneric(String id, String type, List<Symulacja.Czynnosc.Otoczenie.Rozklad.Param> parametry) throws SimControlException {
		this.id = id;
		
		try {

			if (type.equalsIgnoreCase("UNIFORM")) {

				if (parametry.size() != 2) {
					throw new Exception(
							"Niepoprawna ilosc parametrow dla rozkladu UNIFORM!");
				}

				double x = Double.parseDouble(parametry.get(0).getWartosc());
				double y = Double.parseDouble(parametry.get(1).getWartosc());

				distribution = new Uniform(x, y);
			} else if (type.equalsIgnoreCase("ERLANG")) {

				if (parametry.size() != 2) {
					throw new Exception(
							"Niepoprawna ilosc parametrow dla rozkladu ERLANG!");
				}

				int x = Integer.parseInt(parametry.get(0).getWartosc());
				double y = Double.parseDouble(parametry.get(1).getWartosc());

				distribution = new Erlang(x, y);
			} else if (type.equalsIgnoreCase("NORMAL")) {
				if (parametry.size() != 2) {
					throw new Exception(
							"Niepoprawna ilosc parametrow dla rozkladu ERLANG!");
				}

				double x = Double.parseDouble(parametry.get(0).getWartosc());
				double y = Double.parseDouble(parametry.get(1).getWartosc());

				distribution = new Normal(x, y);
			} else {
				throw new Exception("Wprowadzono nieznany typ rozkladu!");
			}

		} catch (Exception e) {
			System.err.println("WPROWADZONO ZŁE DANE!");
		}
		
		zglaszaj = new ZglaszajGeneric(this, 0.0);
		MVczasy_miedzy_zgl = new MonitoredVar();
	}

	public double getNextDouble() {
		return distribution.getNextDouble();
	}

	@Override
	public Vector<ValidationMessage> validate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
