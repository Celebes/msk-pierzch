package pl.edu.wat.msk.smo_generic;

import smo.KoniecNiecierpliwosci;
import smo.Smo;
import smo.StartNiecierpliwosci;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class ZgloszenieGeneric extends BasicSimObj {
	
	double czasOdniesienia;
    static int nr=0;
    int tenNr;
    public KoniecNiecierpliwosciGeneric koniecNiecierpliwosci;

    public int getTenNr() {
		return tenNr;
	}

	public void setTenNr() {
		this.tenNr = nr++;
	}

	public ZgloszenieGeneric(double Czas) throws SimControlException
    {
        czasOdniesienia = Czas;
        setTenNr();
        StartNiecierpliwosciGeneric stN = new StartNiecierpliwosciGeneric(this);
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
