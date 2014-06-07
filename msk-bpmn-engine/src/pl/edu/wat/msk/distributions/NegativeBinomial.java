package pl.edu.wat.msk.distributions;

public class NegativeBinomial extends AbstractDistribution {

	private double p;
	private int n;

	public NegativeBinomial(double p, int n) {
		this.p = p;
		this.n = n;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().negativebinomial(p, n);
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
