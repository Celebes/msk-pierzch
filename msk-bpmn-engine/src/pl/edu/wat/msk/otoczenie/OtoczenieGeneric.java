package pl.edu.wat.msk.otoczenie;

import pl.edu.wat.msk.distributions.AbstractDistribution;
import pl.edu.wat.msk.distributions.Binomial;
import pl.edu.wat.msk.distributions.ChiSquare;
import pl.edu.wat.msk.distributions.Erlang;
import pl.edu.wat.msk.distributions.Exponential;
import pl.edu.wat.msk.distributions.Gamma;
import pl.edu.wat.msk.distributions.Geometric;
import pl.edu.wat.msk.distributions.Laplace;
import pl.edu.wat.msk.distributions.LogNormal;
import pl.edu.wat.msk.distributions.NegativeBinomial;
import pl.edu.wat.msk.distributions.Normal;
import pl.edu.wat.msk.distributions.Poisson;
import pl.edu.wat.msk.distributions.Student;
import pl.edu.wat.msk.distributions.Triangular;
import pl.edu.wat.msk.distributions.Uniform;
import pl.edu.wat.msk.distributions.Weibull;

public class OtoczenieGeneric
{
    
    AbstractDistribution distribution;
    
    public OtoczenieGeneric(String type, String a, String b, String c)
    {
        type = type.toUpperCase();
        try
        {
            if (type == "BETA") {
                Integer x = Integer.parseInt(a);
                double y = Double.parseDouble(b);
                distribution = new Erlang(x,y);
            }
            else if (type == "BINOMIAL") {
                double x = Double.parseDouble(a);
                Integer y = Integer.parseInt(b);
                distribution = new Binomial( x, y );
            }
            else if (type == "CHISQUARE") {
                Integer x = Integer.parseInt(a);
                distribution = new ChiSquare(x);
            }
            else if (type == "ERLANG") {
                Integer x = Integer.parseInt(a);
                double y = Double.parseDouble(b);
                distribution = new Erlang(x,y);
            }
            else if (type == "EXPONENTIAL") {
                double x = Double.parseDouble(a);
                distribution = new Exponential(x);
            }
            else if (type == "GAMMA") {
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);
                distribution = new Gamma( x, y );
            }
            else if (type == "GEOMETRIC") {
                double x = Double.parseDouble(a);
                distribution = new Geometric( x );
            }
            else if (type == "LAPLACE") {
                double x = Double.parseDouble(a);
                distribution = new Laplace( x );
            }
            else if (type == "LOGNORMAL") {
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);
                distribution = new LogNormal( x, y );
            }
            else if (type == "NEGATIVEBINOMIAN") {
                double x = Double.parseDouble(a);
                Integer y = Integer.parseInt(b);
                distribution = new NegativeBinomial(x,y);
            }
            else if (type == "NORMAL") {
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);
                distribution = new Normal( x, y );
            }
            else if (type == "POISSON") {
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);
                distribution = new Poisson( x, y );
            }
            else if (type == "STUDENT") {
                int x = Integer.parseInt(a);
                distribution = new Student(x);
            }
            else if (type == "TRIANGULAR") {
                double x = Double.parseDouble(a);
                distribution = new Triangular(x);
            }
            else if (type == "UNIFORM") {
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);
                distribution = new Uniform( x, y );
            }
            else if (type == "WEIBULL") {
                double x = Double.parseDouble(a);
                double y = Double.parseDouble(b);
                distribution = new Weibull( x,y);
            }
        }
        catch ( Exception e )
        {
            System.err.println("WPROWADZONO ZŁE DANE!");
        }
    }
    
    
    public double getNextDouble() {
        return distribution.getNextDouble();
    }

}
