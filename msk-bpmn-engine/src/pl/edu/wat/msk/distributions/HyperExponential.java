package pl.edu.wat.msk.distributions;

public class HyperExponential extends AbstractDistribution {

	private double p;
	private double a;
	private double b;

	public HyperExponential(double p, double a, double b) {
		this.p = p;
		this.a = a;
		this.b = b;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().hyperExponential(p, a, b);
	}

}
