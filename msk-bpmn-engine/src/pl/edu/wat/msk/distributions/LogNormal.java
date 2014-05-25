package pl.edu.wat.msk.distributions;

public class LogNormal extends AbstractDistribution {

	private double a;
	private double b;

	public LogNormal(double a, double b) {

		this.a = a;
		this.b = b;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().lognormal(a, b);
	}

}
