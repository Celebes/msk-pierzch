package pl.edu.wat.msk.distributions;

public class Geometric extends AbstractDistribution {

	private double p;

	public Geometric(double p) {
		this.p = p;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().geometric(p);
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

}
