package pl.edu.wat.msk.elements;

import java.util.Map;
import java.util.TreeMap;

public abstract class UseProbability extends HavePrevNext implements IUseProbability {
	
	protected Map<String, Float> probabilities;
	
	public UseProbability() {
		this.probabilities = new TreeMap<>();
	}

	@Override
	public void addProbability(String id, float probability) {
		probabilities.put(id, new Float(probability));
	}

	@Override
	public float getProbability(String id) {
		if(probabilities.containsKey(id)) {
			return probabilities.get(id);
		} else {
			// jesli p-stwo nie jest sprecyzowane, to zawsze TRUE
			return 1.0f;
		}
	}

	public Map<String, Float> getProbabilities() {
		return probabilities;
	}

	public void setProbabilities(Map<String, Float> probabilities) {
		this.probabilities = probabilities;
	}

}
