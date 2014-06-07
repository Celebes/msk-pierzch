/**
 * Bramka logiczna XOR.
 */
package pl.edu.wat.msk.bramki_logiczne;

import java.util.List;

import pl.edu.wat.msk.smo_generic.LogicGateGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Param;

/**
 * @author Łukasz Kotowski
 * 
 */
public class XOR extends LogicGateGeneric {

    public XOR(String id, String rodzaj, String distributionName,
	    List<Param> params) {
	super(id, rodzaj, distributionName, params);
    }

}
