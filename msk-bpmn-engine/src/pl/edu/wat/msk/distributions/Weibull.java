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

}
