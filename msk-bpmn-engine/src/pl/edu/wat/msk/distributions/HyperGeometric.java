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

}
