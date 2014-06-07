package pl.edu.wat.msk.distributions;

public class Student extends AbstractDistribution {
	private int n;

	public Student(int n) {
		this.n = n;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().student(n);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
