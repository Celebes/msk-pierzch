package pl.edu.wat.msk.bramki_logiczne;

import java.util.ArrayList;
import java.util.List;

import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import dissimlab.simcore.BasicSimObj;

public class BramkaLogiczna extends BasicSimObj {
	
	private String id;
	private String rodzaj;
	
	private List<BasicSimObj> nastepniki;
	
	public BramkaLogiczna(Symulacja.Czynnosc.Bramka bramkaDaneXML) {
		this.id = bramkaDaneXML.getId();
		this.rodzaj = bramkaDaneXML.getRodzaj();
		
		nastepniki = new ArrayList<>();
	}
	
	public void dodajNastepnika(BasicSimObj nastepnik) {
		nastepniki.add(nastepnik);
	}

}
