package pl.edu.wat.wcy.mtsk.parser;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja.Czynnosc.Otoczenie;

public class XmlParser {
	public static void main(String[] args) {
		try {

			URI path = XmlParser.class.getClassLoader().getResource("./diagram_prosty.xml").toURI();
			File file = new File(path);

			JAXBContext jaxbContext = JAXBContext.newInstance(Symulacja.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Symulacja symulacja = (Symulacja) jaxbUnmarshaller.unmarshal(file);

			// dla testu wypisz cos
			List<Symulacja.Czynnosc.Otoczenie> wszystkieOtoczenia = symulacja.getCzynnosc().getOtoczenie();
			
			for(Otoczenie o : wszystkieOtoczenia) {
				Symulacja.Czynnosc.Otoczenie.Rozklad rozklad = o.getRozklad();
				
				System.out.println("ROZKLAD: ID=" + rozklad.getId() + " | RODZAJ=" + rozklad.getRodzaj());
				
				for(Symulacja.Czynnosc.Otoczenie.Rozklad.Param p : rozklad.getParam()) {
					System.out.println("PARAM: NAZWA=" + p.getNazwa() + " | WARTOSC=" + p.getWartosc());
				}
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
