package pl.edu.wat.msk.distributions;

/**
 * 
 * @since 24.05.2014, 19:50 Generated by AgroUML
 * 
 */
public class Gamma extends AbstractDistribution {

	public double a;

	public double b;

	public Gamma(double a, double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().gamma(a, b);
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

}