package pl.edu.wat.msk.distributions;

/**
 * 
 * @since 24.05.2014, 19:50 Generated by AgroUML
 * 
 */
public class Erlang extends AbstractDistribution {

	public Integer n;

	public double a;

	public Erlang(Integer n, double a) {
		this.n = n;
		this.a = a;
	}

	@Override
	public double getNextDouble() {
		return super.getSimGen().erlang(n, a);
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

}