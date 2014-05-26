package pl.edu.wat.wcy.mtsk.parser;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dissimlab.simcore.SimControlException;
import pl.edu.wat.msk.smo_generic.OtoczenieGeneric;
import pl.edu.wat.msk.smo_generic.SmoGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja.Czynnosc.Kolejka;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja.Czynnosc.Otoczenie;
import sun.applet.Main;

public class XmlHelper {
	
	public static Symulacja generujSymulacjeZPliku(String sciezkaPliku) throws JAXBException, URISyntaxException {
		
		URI path = SymulacjaRunner.class.getClassLoader().getResource(sciezkaPliku).toURI();
		File file = new File(path);

		JAXBContext jaxbContext = JAXBContext.newInstance(Symulacja.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Symulacja symulacja = (Symulacja) jaxbUnmarshaller.unmarshal(file);
		
		return symulacja;
	}
	
	public static List<OtoczenieGeneric> generujOtoczenia(List<Symulacja.Czynnosc.Otoczenie> wszystkieOtoczenia) throws SimControlException {
		
		List<OtoczenieGeneric> wygenerowaneOtoczenia = new ArrayList<>();
		
		for(Otoczenie o : wszystkieOtoczenia) {
			Symulacja.Czynnosc.Otoczenie.Rozklad rozklad = o.getRozklad();			
			wygenerowaneOtoczenia.add(new OtoczenieGeneric(o.getId(), rozklad.getRodzaj(), rozklad.getParam()));
		}
		
		return wygenerowaneOtoczenia;
	}

	public static List<SmoGeneric> generujKolejki(List<Kolejka> kolejka) throws SimControlException {
		List<SmoGeneric> wygenerowaneKolejki = new ArrayList<>();
		
		for(Kolejka k : kolejka) {
			System.out.println(k.getId());
			
			// sprawdz czy skonczona czy nie
			if(k.isNieskonczona()) {
				System.out.println("Generuje nieskonczona");
				wygenerowaneKolejki.add(new SmoGeneric(k.getId()));
			} else {
				int maxDl = Integer.parseInt(k.getMaxDlugoscKolejki());
				System.out.println("Generuje skonczona o dlugosci: " + maxDl);
				wygenerowaneKolejki.add(new SmoGeneric(k.getId(), maxDl));
			}
		}
		
		return wygenerowaneKolejki;
	}
	
}
