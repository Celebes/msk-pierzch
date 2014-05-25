package pl.edu.wat.msk.distributions;

public class Binomial extends AbstractDistribution {

	private double p;
	private int n;

	public Binomial(double p, int n) {
		this.p = p;
		this.n = n;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().binomial(p, n);
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
