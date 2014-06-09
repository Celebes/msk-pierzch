package pl.edu.wat.msk.bramki_logiczne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ListModel;

import pl.edu.wat.msk.elements.IModelComponent;
import pl.edu.wat.msk.smo_generic.LogicGateGeneric;
import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * 
 * @author Krzysztof Jedynak Bramka logiczna AND
 * 
 */
public class AND extends LogicGateGeneric
{

    private static HashMap<String, ArrayList<ZgloszenieGeneric>> repoZgloszen;
    private int numberOfInputs;
    private int numberOfOutputs;

    public AND( String id, String rodzaj )
    {
        super( id, rodzaj );
        // inicjalizacja wewnętrznego repozytorium bramki
        // <id wejścia, <id zgloszenia, obiekt zgłoszenia>>
        repoZgloszen = new HashMap<>();
        numberOfInputs = getPrev().size();
        numberOfOutputs = getNext().size();
    }

    @Override
    public void processing( ZgloszenieGeneric zgl, String id )
    {
        // dodaj nowe zgłoszenie do repozytorium dla wejścia o id
        this.addNotificationToANDGate( zgl, id );
        // sprawdź czy można przenieść zgłoszenie o key na wyjścia
        if ( isWaitNotification( zgl.getTenNr() ) )
        {
            System.out.println( "Zgłoszenie może zostać przeniesione na wyjście!" );
            // usuń zgłoszenie z repozytorium ze wszystkich wejść
            this.removeNotificationFromANDGateById( zgl.getTenNr() );
            // przenieś zgłoszenie na wszystkie wyjścia
            super.putToNexts( zgl );
        }
        else
        {
            System.out.println( "Zgłoszenie musi czekać na bramce!" );
        }

    }

    /**
     * Sprawdź czy zgłoszenie ma oczekiwać w repozytorium czy można juz je
     * wygenerować na wyjścia
     * 
     * @param key
     * @return
     */
    private boolean isWaitNotification( Integer key )
    {
        Integer currentnumberOfNotifics = 0;
        for ( String id : repoZgloszen.keySet() )
        {
            ArrayList<ZgloszenieGeneric> listOfNotificsForInput = repoZgloszen.get( id );
            for ( int i = 0; i < listOfNotificsForInput.size(); i++ )
            {
                if ( listOfNotificsForInput.get( i ).getTenNr() == key )
                {
                    currentnumberOfNotifics++;
                }
            }
        }

        if ( currentnumberOfNotifics == getPrev().size() )
            return true;
        else
            return false;
    }

    /**
     * Dodawanie zgłoszenia z wejścia do wewnętrznego repozytorium zgłoszeń
     * 
     * @param zgl
     * @param id
     */
    private void addNotificationToANDGate( ZgloszenieGeneric zgl, String id )
    {
        Integer keyOfNotif = zgl.getTenNr();

        if ( !repoZgloszen.containsKey( id ) )
        {
            System.out.println( "Wygenerowanie nowego wejścia dla bramki AND i przypisanie zgłoszenia" );
            ArrayList<ZgloszenieGeneric> listOfNotificsForInput = new ArrayList<ZgloszenieGeneric>();
            listOfNotificsForInput.add( zgl );
            // HashMap<Integer, ArrayList<ZgloszenieGeneric>> input = new
            // HashMap<Integer, ArrayList<ZgloszenieGeneric>>();
            // input.put( keyOfNotif, listOfNotificsForInput );
            repoZgloszen.put( id, listOfNotificsForInput );
        }
        else
        {
            System.out.println( "Aktualizcja liczby zgłoszeń dla wejścia o id: " + id + " zgloszenia o kluczu: "
                                + keyOfNotif );
            repoZgloszen.get( id ).add( zgl );
        }
    }

    /**
     * Usuwanie zgłoszenia z wewnętrznego repozytorium po kluczu zgloszenia ze
     * wszystkich wejść
     * 
     * @param key
     */
    private void removeNotificationFromANDGateById( int key )
    {
        System.out.println( "Usunięcie zgłoszenia z repozytorium o kluczu: " + key );
        for ( String id : repoZgloszen.keySet() )
        {

            for ( int i = 0; i < repoZgloszen.get( id ).size(); i++ )
            {
                if ( repoZgloszen.get( id ).get( i ).getTenNr() == key )
                {
                    repoZgloszen.get( id ).remove( i );
                }
            }
        }
    }

}
