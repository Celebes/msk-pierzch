package pl.edu.wat.msk.distributions;

public class ChiSquare extends AbstractDistribution {

	private int n;

	public ChiSquare(int n) {
		this.n = n;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().chisquare(n);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
