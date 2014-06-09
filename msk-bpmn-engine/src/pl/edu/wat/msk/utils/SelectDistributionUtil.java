/**
 * Klasa pomocnicza dla wybierania rokładu.
 */
package pl.edu.wat.msk.utils;

import java.util.List;

import pl.edu.wat.msk.distributions.AbstractDistribution;
import pl.edu.wat.msk.distributions.Erlang;
import pl.edu.wat.msk.distributions.Normal;
import pl.edu.wat.msk.distributions.Uniform;
import pl.edu.wat.wcy.mtsk.xml_elements.Param;

/**
 * @author Łukasz Kotowski
 * 
 */
public class SelectDistributionUtil {

    public static AbstractDistribution getDistributionByName(String distributionName,
	    List<Param> params) {
	try {

	    if (distributionName.equalsIgnoreCase("UNIFORM")) {

		if (params.size() != 2) {
		    throw new Exception(
			    "Niepoprawna ilosc parametrow dla rozkladu UNIFORM!");
		}

		double x = Double.parseDouble(params.get(0).getWartosc());
		double y = Double.parseDouble(params.get(1).getWartosc());

		return new Uniform(x, y);
	    } else if (distributionName.equalsIgnoreCase("ERLANG")) {

		if (params.size() != 2) {
		    throw new Exception(
			    "Niepoprawna ilosc parametrow dla rozkladu ERLANG!");
		}

		int x = Integer.parseInt(params.get(0).getWartosc());
		double y = Double.parseDouble(params.get(1).getWartosc());

		return new Erlang(x, y);
	    } else if (distributionName.equalsIgnoreCase("NORMAL")) {
		if (params.size() != 2) {
		    throw new Exception(
			    "Niepoprawna ilosc parametrow dla rozkladu ERLANG!");
		}

		double x = Double.parseDouble(params.get(0).getWartosc());
		double y = Double.parseDouble(params.get(1).getWartosc());

		return new Normal(x, y);
	    } else {
		throw new Exception("Wprowadzono nieznany typ rozkladu!");
	    }

	} catch (Exception e) {
	    //System.err.println("WPROWADZONO ZŁE DANE!");
	}

	return null;

    }

}
