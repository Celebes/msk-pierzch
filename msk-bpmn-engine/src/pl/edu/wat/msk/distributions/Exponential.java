package pl.edu.wat.msk.distributions;

/**
 * 
 * @since 24.05.2014, 19:50 Generated by AgroUML
 * 
 */
public class Exponential extends AbstractDistribution implements IDistribution {

	public double a;

	public Exponential(double a) {
		this.a = a;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().exponential(a);
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

}