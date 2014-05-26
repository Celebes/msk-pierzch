package pl.edu.wat.wcy.mtsk.parser;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import pl.edu.wat.msk.smo_generic.OtoczenieGeneric;
import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class SymulacjaRunner {
	public static void main(String[] args) {
		try {
			
			//Symulacja symulacja = XmlHelper.generujSymulacjeZPliku("./diagram_prosty.xml");
			//uruchomSymulacje(symulacja);
			
			Symulacja symulacja = XmlHelper.generujSymulacjeZPliku("./diagram_najprostszy.xml");
			uruchomSymulacjeNajprostsza(symulacja);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SimControlException e) {
			e.printStackTrace();
		}
	}
	
	public static void uruchomSymulacje(Symulacja symulacja) throws SimControlException {
		SimManager model = SimManager.getInstance();

		// generuj otoczenia
		List<OtoczenieGeneric> wygenerowaneOtoczenia = XmlHelper.generujOtoczenia(symulacja.getCzynnosc().getOtoczenie());

	}
	
	public static void uruchomSymulacjeNajprostsza(Symulacja symulacja) throws SimControlException {
		SimManager model = SimManager.getInstance();
		
		// -------- INICJALIZACJA Z XML --------
		
		// generacja SMO nieskonczonego
		List<SmoInfiniteGeneric> wygenerowaneNieskonczoneSMO = XmlHelper.generujKolejkiNieskonczone(symulacja.getCzynnosc().getKolejka());
		
		// utworzenie otoczenia, tutaj juz zaczynaja pojawiac sie pierwsze zdarzenia
		List<OtoczenieGeneric> wygenerowaneOtoczenia = XmlHelper.generujOtoczenia(symulacja.getCzynnosc().getOtoczenie());
		
		
		// -------------------------------------
		
		SimControlEvent stopEvent = new SimControlEvent(60.0, SimControlStatus.STOPSIMULATION);
		// Badanie czasu trwania eksperymentu - pocz�tek
		long czst = new Date().getTime();
		// Uruchomienie symulacji za po�rednictwem metody "start" z
		model.startSimulation();
		// Badanie czasu trwania eksperymentu - koniec
		czst = new Date().getTime() - czst;
		// Wyniki
		System.out.println("Czas trwania eksperymentu: " + czst);
	}
}
