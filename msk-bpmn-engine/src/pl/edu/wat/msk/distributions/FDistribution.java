package pl.edu.wat.msk.distributions;

public class FDistribution extends AbstractDistribution {

	private int n;
	private int m;

	public FDistribution(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().fdistribution(n, m);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

}
