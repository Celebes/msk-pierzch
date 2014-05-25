package pl.edu.wat.msk.otoczenie;

import java.util.List;

import pl.edu.wat.msk.distributions.AbstractDistribution;
import pl.edu.wat.msk.distributions.Erlang;
import pl.edu.wat.msk.distributions.Exponential;

public class OtoczenieGeneric
{
    
    AbstractDistribution distribution;
    
    public OtoczenieGeneric(String type, String a, String b, String c)
    {
        if (type == "ERLANG") {
            Integer x = Integer.parseInt(a);
            double y = Double.parseDouble(b);
            distribution = new Erlang(x,y);
        }
        if (type == "EXPONENTIAL") {
            double x = Double.parseDouble( a );
            distribution = new Exponential( x );
        }
    }
    
    
    public double getNextDouble() {
        return distribution.getNextDouble();
    }

}
