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

}
