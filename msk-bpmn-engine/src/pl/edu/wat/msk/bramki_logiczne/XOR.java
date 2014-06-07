/**
 * Bramka logiczna XOR.
 */
package pl.edu.wat.msk.bramki_logiczne;

import java.util.List;

import pl.edu.wat.msk.smo_generic.LogicGateGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Param;

/**
 * Klasa dla bramki XOR.
 * 
 * @author Łukasz Kotowski
 * 
 */
public class XOR extends LogicGateGeneric {

    public XOR(String id, String rodzaj, String distributionName,
	    List<Param> params) {
	super(id, rodzaj, distributionName, params);
    }

    /**
     * Metoda przesłonięta. Wysyła tylko do jednego komponentu (tam gdzie
     * zostanie to wylosowane z prawdopodobieństwa).
     */
    @Override
    public void putToNexts(ZgloszenieGeneric zgl) {
	int size = nexts.size();

	double rand = getDistribution().getNextDouble();
	int variableToProcess = getNextToProcess(size, rand);
	if (variableToProcess > 0) {
	    nexts.get(variableToProcess).processing(zgl);
	    System.out
		    .println("BRAMKA XOR: PRZEKAZANO PROCESSOWANIE NA GAŁĄŹ NR: "
			    + variableToProcess);
	} else { // wypisanie powiadomienia o błędzie na konsolę.
	    System.out.println("### ERROR ### BRAMKA XOR: NIE PRZESŁANO DALEJ!");
	}
    }

    @Override
    public void processing(ZgloszenieGeneric zgl) {

    };

    /* *****************METODY PRYWATNE *********************** */

    protected int getNextToProcess(int size, double rand) {
	double wartoscZakresu = 1 / size;
	double from = 0;
	double to = wartoscZakresu;
	for (int i = 0; i < size; i++) {
	    if (between(rand, from, to)) {
		return i;
	    }
	    from = from + wartoscZakresu;
	    to = to + wartoscZakresu;
	}
	return -1;
    }

    private boolean between(double value, double from, double to) {
	if (value > from && value < to) {
	    return true;
	}
	return false;
    }

}
