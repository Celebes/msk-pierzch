package pl.edu.wat.msk.smo_generic;

import pl.edu.wat.msk.elements.HavePrevNext;
import pl.edu.wat.msk.elements.IModelComponent;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class ZgloszenieGeneric extends BasicSimObj {
	
	double czasOdniesienia;
    static int nr=0;
    int tenNr;
    //public KoniecNiecierpliwosciGeneric koniecNiecierpliwosci;
    //public HavePrevNext aktualnieObslugujacyKomponent;

    public int getTenNr() {
		return tenNr;
	}

	public void setTenNr() {
		this.tenNr = nr++;
	}

	public ZgloszenieGeneric(double Czas, HavePrevNext aktualnieObslugujacyKomponent) throws SimControlException
    {
        czasOdniesienia = Czas;
        setTenNr();
        //StartNiecierpliwosciGeneric stN = new StartNiecierpliwosciGeneric(this);
        //this.aktualnieObslugujacyKomponent = aktualnieObslugujacyKomponent;
    }

    public void setCzasOdniesienia(double t)
    {
        czasOdniesienia = t;
    }

    public double getCzasOdniesienia()
    {
        return czasOdniesienia;
    }

}
