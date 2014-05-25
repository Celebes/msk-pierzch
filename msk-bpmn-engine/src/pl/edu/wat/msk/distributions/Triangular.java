package pl.edu.wat.msk.distributions;

public class Triangular extends AbstractDistribution {

	private double a;

	public Triangular(double a) {
		this.a = a;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().triangular(a);
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

}
