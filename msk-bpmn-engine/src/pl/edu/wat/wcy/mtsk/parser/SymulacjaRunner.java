package pl.edu.wat.wcy.mtsk.parser;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IHaveNext;
import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.smo_generic.CompositActivity;
import pl.edu.wat.msk.smo_generic.OtoczenieGeneric;
import pl.edu.wat.msk.smo_generic.SmoInfiniteGeneric;
import pl.edu.wat.wcy.mtsk.xml_elements.Bramka;
import pl.edu.wat.wcy.mtsk.xml_elements.Czynnosc;
import pl.edu.wat.wcy.mtsk.xml_elements.PodCzynnosc;
import pl.edu.wat.wcy.mtsk.xml_elements.Polaczenie;
import pl.edu.wat.wcy.mtsk.xml_elements.Symulacja;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class SymulacjaRunner {
	public static void main(String[] args) {
		try {			
			Symulacja symulacja = XmlHelper.generujSymulacjeZPliku("./diagram_najprostszy.xml");
			uruchomSymulacje(symulacja);
			
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
		
		// -------- INICJALIZACJA Z XML --------
		
		// najpierw przejdz po wszystkich czynnosciach i potworz wszystko oprocz podczynnosci, omin tez polaczenia zwiazane z podczynnosciami
		
		List<CompositActivity> listaWszystkichCzynnosci = new ArrayList<>();
		
		for(Czynnosc czynnosc : symulacja.getCzynnosc()) {
			List<HavePrevNext> listaElementowCzynnosci = new ArrayList<>();
			
			// generuj SMO nieskonczone
			List<SmoInfiniteGeneric> wygenerowaneNieskonczoneSMO = XmlHelper.generujKolejkiNieskonczone(czynnosc.getKolejka());
			
			// generuj otoczenia
			List<OtoczenieGeneric> wygenerowaneOtoczenia = XmlHelper.generujOtoczenia(czynnosc.getOtoczenie());
			
			// zapisz liste ID podczynnosci do sprawdzenia
			List<String> idPodczynnosci = XmlHelper.generujIdPodczynnosci(czynnosc.getPodCzynnosc());
			
			// generuj polaczenia
			// polaczenia!!!
			for(Polaczenie p : czynnosc.getPolaczenie()) {
				String idOD = p.getOd();
				String idDO = p.getDo();
				
				IHaveNext objOd = null;
				IModelComponent objDo = null;
				
				boolean dotyczyPodczynnosci = false;
				
				// na poczatku sprawdz czy polaczenie nie dotyczy podczynnosci - jesli tak, to narazie pomin
				for(String s : idPodczynnosci) {
					if(s.equals(idOD) || s.equals(idDO)) {
						System.out.println("Aktualne polaczenie (" + idOD + " -> " + idDO + ") dotyczy podczynnosci - pomijam!");
						dotyczyPodczynnosci = true;
						break;
					}
				}
				
				if(dotyczyPodczynnosci) {
					continue;
				}
				
				// iteruj po wszystkim w poszukiwaniu OD i DO
				
				for(SmoInfiniteGeneric smo : wygenerowaneNieskonczoneSMO) {
					if(smo.getId().equals(idOD)) {
						objOd = smo;
					}
					
					if(smo.getId().equals(idDO)) {
						objDo = smo;
					}
				}
				
				for(OtoczenieGeneric ot : wygenerowaneOtoczenia) {
					if(ot.getId().equals(idOD)) {
						objOd = ot;
					}
					
					if(ot.getId().equals(idDO)) {
						objDo = ot;
					}
				}
				
				// bramki
				
				// semafory
				
				// ...
				
				if(objOd == null || objDo == null) {
					throw new SimControlException("ID podane w polaczeniu nie odnosi sie do zadnego istniejacego elementu w diagramie!");
				} else {
					objOd.addNext(objDo);
					
					// dodaj prawdopodobienstwo z polaczenia do bramki
					if(objOd instanceof Bramka) {
						if(p.getWarunek() != null) {
							
						}
					}
					
					System.out.println("Pomyslnie utworzono polaczenie pomiedzy obiektami od ID: " + idOD + ", " + idDO);
				}
			}
			
			// dodaj wszystkie wygenerowane elementy do listy wszystkich klockow w danej czynnosci
			listaElementowCzynnosci.addAll(wygenerowaneNieskonczoneSMO);
			listaElementowCzynnosci.addAll(wygenerowaneOtoczenia);
			
			// znajdz element pierwszy w danej czynnosci
			
			HavePrevNext first = null;
			HavePrevNext last = null;
			
			for(HavePrevNext element : listaElementowCzynnosci) {
				if(element.getPrev().isEmpty()) {
					first = element;
					break;
				}
			}
			
			for(HavePrevNext element : listaElementowCzynnosci) {
				if(element.getNext().isEmpty()) {
					last = element;
					break;
				}
			}
			
			if(first == null || last == null) {
				throw new SimControlException("Dana czynnosc nie posiada elementu pierwszego lub ostatniego, co oznacza, ze jest pusta!");
			}
			
			System.out.println("Pierwszym elementem w czynnosci " + czynnosc.getId() + " jest: " + first.getId() + ", a ostatnim: " + last.getId());
			
			// dodaj czynnosc do listy
			listaWszystkichCzynnosci.add(new CompositActivity(czynnosc.getId(), first, last, listaElementowCzynnosci));
		}
		
		// teraz jak juz wszystkie czynnosci zostaly potworzone to mozna je ze soba polaczyc w zaleznosci od polaczen dot. podczynnosci
		for(Czynnosc czynnosc : symulacja.getCzynnosc()) {
			
			// zapisz liste ID podczynnosci do sprawdzenia
			List<String> idPodczynnosci = XmlHelper.generujIdPodczynnosci(czynnosc.getPodCzynnosc());
			
			for(Polaczenie p : czynnosc.getPolaczenie()) {
				String idOD = p.getOd();
				String idDO = p.getDo();
				String idp = null;
				String idc = null;
				
				boolean dotyczyPodczynnosci = false;
				
				// na poczatku sprawdz czy polaczenie dotyczy podczynnosci - jesli tak, to kontynuuj
				for(String s : idPodczynnosci) {
					if(s.equals(idOD) || s.equals(idDO)) {
						System.out.println("Aktualne polaczenie (" + idOD + " -> " + idDO + ") dotyczy podczynnosci!");
						dotyczyPodczynnosci = true;
						idp = s;
						break;
					}
				}
				
				if(!dotyczyPodczynnosci) {
					continue;
				}
				
				// znajdz REF podczynnosci
				for(PodCzynnosc pc : czynnosc.getPodCzynnosc()) {
					if(pc.getId().equals(idp)) {
						idc = pc.getRef();
					}
				}
				
				if(idc == null) {
					throw new SimControlException("Cos sie zepsulo!");
				}
				
				// pobierz aktualna czynnosc z listy wszystkich czynnosci
				CompositActivity aktualna = null;
				
				for(CompositActivity ca : listaWszystkichCzynnosci) {
					if(ca.getId().equals(czynnosc.getId())) {
						aktualna = ca;
						break;
					}
				}
				
				if(aktualna == null) {
					throw new SimControlException("Nie znaleziono aktualnej czynnosci o takm ID!");
				}
				
				// znajdz podczynnosc
				CompositActivity podczynnosc = null;
				
				for(CompositActivity ca : listaWszystkichCzynnosci) {
					if(ca.getId().equals(idc)) {
						podczynnosc = ca;
						break;
					}
				}
				
				if(podczynnosc == null) {
					throw new SimControlException("Nie znaleziono podczynnosci o takm ID!");
				}
				
				// pobierz element, ktorego dotyczy polaczenie
				HavePrevNext objOD = null;
				HavePrevNext objDO = null;
				
				for(HavePrevNext element : aktualna.getSubComponents()) {
					if(element.getId().equals(idOD)) {
						objOD = element;
						break;
					}
					
					if(element.getId().equals(idDO)) {
						objDO = element;
						break;
					}
				}
				
				if(objOD == null && objDO == null) {
					throw new SimControlException("Nie znaleziono elementu nalezacego do polaczenia!");
				}
				
				if(objOD == null) {
					objOD = podczynnosc.getFirstComponent();
				} else if(objDO == null) {
					objDO = podczynnosc.getLastComponent();
				}
				
				// dodaj polaczenie
				objOD.addNext(objDO);
			}
		}
		
		//wygenerowaneOtoczenia.get(0).addNext(wygenerowaneNieskonczoneSMO.get(0));
		//wygenerowaneNieskonczoneSMO.get(0).addNext(wygenerowaneNieskonczoneSMO.get(1));
		
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
