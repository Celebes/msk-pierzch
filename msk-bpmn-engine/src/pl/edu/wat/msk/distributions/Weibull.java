package pl.edu.wat.msk.distributions;

public class Weibull extends AbstractDistribution {

	private double a;
	private double b;

	public Weibull(double a, double b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().weibull(a, b);
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
