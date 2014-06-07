package pl.edu.wat.msk.utils;

public enum DistributionParamsDict {

	beta("Beta"),
	dwumianowy("Binomial"),
	chiKwadrat("ChiSquare"),
	erlang("Erlang");
	
	
	
	private String id;
	
	
	private DistributionParamsDict(String id) {
		this.id = id;
	}
	
	
	
	
	
	

}
