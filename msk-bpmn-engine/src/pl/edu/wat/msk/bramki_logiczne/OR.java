/**
 * Bramka logiczna OR.
 */
package pl.edu.wat.msk.bramki_logiczne;

import java.util.LinkedList;
import java.util.List;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Param;

/**
 * Klasa dla bramki OR.
 * 
 * @author Łukasz Kotowski
 * 
 */
public class OR extends XOR {

    public OR(String id, String rodzaj, String distributionName,
	    List<Param> params) {
	super(id, rodzaj, distributionName, params);
    }

    @Override
    public void putToNexts(ZgloszenieGeneric zgl) {
	int size = nexts.size();
	List<Integer> selectedVariable = new LinkedList<>();
	for (int i = 0; i < size; i++) {
	    double rand = getDistribution().getNextDouble();
	    int variableToProcess = getNextToProcess(size, rand);

	    if (variableToProcess > 0
		    && !selectedVariable.contains(variableToProcess)) {
		nexts.get(variableToProcess).processing(zgl, getId());
		System.out
			.println("BRAMKA OR: PRZEKAZANO PROCESSOWANIE NA GAŁĄŹ NR: "
				+ variableToProcess);
	    }
	    selectedVariable.add(variableToProcess);
	}
    }

}
