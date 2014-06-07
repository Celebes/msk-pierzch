package pl.edu.wat.wcy.mtsk.parser;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import pl.edu.wat.msk.elements.IHaveNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.smo_generic.OtoczenieGeneric;
import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Bramka;
import pl.edu.wat.wcy.mtsk.xml_elements.Polaczenie;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class SymulacjaRunner {
    public static void main(String[] args) {
	try {

	    // Symulacja symulacja =
	    // XmlHelper.generujSymulacjeZPliku("./diagram_prosty.xml");
	    // uruchomSymulacje(symulacja);

	    Symulacja symulacja = XmlHelper
		    .generujSymulacjeZPliku("./diagram_najprostszy.xml");
	    uruchomSymulacjeNajprostsza(symulacja);

	} catch (JAXBException e) {
	    e.printStackTrace();
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	} catch (SimControlException e) {
	    e.printStackTrace();
	}
    }

    public static void uruchomSymulacje(Symulacja symulacja)
	    throws SimControlException {
	SimManager model = SimManager.getInstance();

	// generuj otoczenia
	List<OtoczenieGeneric> wygenerowaneOtoczenia = XmlHelper
		.generujOtoczenia(symulacja.getCzynnosc().getOtoczenie());

    }

    public static void uruchomSymulacjeNajprostsza(Symulacja symulacja)
	    throws SimControlException {
	SimManager model = SimManager.getInstance();

	// -------- INICJALIZACJA Z XML --------

	// generacja SMO nieskonczonego
	List<SmoInfiniteGeneric> wygenerowaneNieskonczoneSMO = XmlHelper
		.generujKolejkiNieskonczone(symulacja.getCzynnosc()
			.getKolejka());

	// utworzenie otoczenia, tutaj juz zaczynaja pojawiac sie pierwsze
	// zdarzenia
	List<OtoczenieGeneric> wygenerowaneOtoczenia = XmlHelper
		.generujOtoczenia(symulacja.getCzynnosc().getOtoczenie());

	// polaczenia!!!
	for (Polaczenie p : symulacja.getCzynnosc().getPolaczenie()) {
	    String idOD = p.getOd();
	    String idDO = p.getDo();

	    IHaveNext objOd = null;
	    IModelComponent objDo = null;

	    // iteruj po wszystkim w poszukiwaniu OD i DO

	    for (SmoInfiniteGeneric smo : wygenerowaneNieskonczoneSMO) {
		if (smo.getId().equals(idOD)) {
		    objOd = smo;
		}

		if (smo.getId().equals(idDO)) {
		    objDo = smo;
		}
	    }

	    for (OtoczenieGeneric ot : wygenerowaneOtoczenia) {
		if (ot.getId().equals(idOD)) {
		    objOd = ot;
		}

		if (ot.getId().equals(idDO)) {
		    objDo = ot;
		}
	    }

	    // bramki

	    // semafory

	    // ...

	    if (objOd == null || objDo == null) {
		throw new SimControlException(
			"ID podane w polaczeniu nie odnosi sie do zadnego istniejacego elementu w diagramie!");
	    } else {
		objOd.addNext(objDo);

		// dodaj prawdopodobienstwo z polaczenia do bramki
		if (objOd instanceof Bramka) {
		    if (p.getWarunek() != null) {

		    }
		}

		System.out
			.println("Pomyslnie utworzono polaczenie pomiedzy obiektami od ID: "
				+ idOD + ", " + idDO);
	    }

	}
	// wygenerowaneOtoczenia.get(0).addNext(wygenerowaneNieskonczoneSMO.get(0));
	// wygenerowaneNieskonczoneSMO.get(0).addNext(wygenerowaneNieskonczoneSMO.get(1));

	// -------------------------------------

	SimControlEvent stopEvent = new SimControlEvent(60.0,
		SimControlStatus.STOPSIMULATION);
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
