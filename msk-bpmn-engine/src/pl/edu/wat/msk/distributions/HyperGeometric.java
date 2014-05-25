package pl.edu.wat.msk.distributions;

public class HyperGeometric extends AbstractDistribution {

	private int m;
	private int n;
	private double p;

	public HyperGeometric(int m, int n, double p) {
		this.m = m;
		this.n = n;
		this.p = p;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().hypergeometric(m, n, p);
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

}
