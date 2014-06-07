package pl.edu.wat.wcy.mtsk.parser;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pl.edu.wat.msk.smo_generic.OtoczenieGeneric;
import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Kolejka;
import pl.edu.wat.wcy.mtsk.xml_elements.Otoczenie;
import pl.edu.wat.wcy.mtsk.xml_elements.Polaczenie;
import pl.edu.wat.wcy.mtsk.xml_elements.Rozklad;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import dissimlab.simcore.SimControlException;

public class XmlHelper {
	
	public static Symulacja generujSymulacjeZPliku(String sciezkaPliku) throws JAXBException, URISyntaxException {
		
		URI path = SymulacjaRunner.class.getClassLoader().getResource(sciezkaPliku).toURI();
		File file = new File(path);

		JAXBContext jaxbContext = JAXBContext.newInstance(Symulacja.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Symulacja symulacja = (Symulacja) jaxbUnmarshaller.unmarshal(file);
		
		return symulacja;
	}
	
	public static List<OtoczenieGeneric> generujOtoczenia(List<Otoczenie> wszystkieOtoczenia) throws SimControlException {
		
		List<OtoczenieGeneric> wygenerowaneOtoczenia = new ArrayList<>();
		
		for(Otoczenie o : wszystkieOtoczenia) {
			Rozklad rozklad = o.getRozklad();			
			wygenerowaneOtoczenia.add(new OtoczenieGeneric(o.getId(), rozklad.getRodzaj(), rozklad.getParam()));
		}
		
		return wygenerowaneOtoczenia;
	}

	public static List<SmoInfiniteGeneric> generujKolejkiNieskonczone(List<Kolejka> kolejka) {
		List<SmoInfiniteGeneric> wygenerowaneKolejkiNieskonczone = new ArrayList<>();
		
		for(Kolejka k : kolejka) {
			System.out.println(k.getId());
			wygenerowaneKolejkiNieskonczone.add(new SmoInfiniteGeneric(k.getId()));
		}
		
		return wygenerowaneKolejkiNieskonczone;
	}

	public static List<Polaczenie> generujPolaczenia(List<Polaczenie> polaczenie) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
