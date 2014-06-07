package pl.edu.wat.msk.elements;

import java.util.ArrayList;
import java.util.List;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.1
 *
 */
public abstract class HaveNext extends ModelComponent implements IHaveNext {

	protected List<IModelComponent> nexts;
	
	public HaveNext() {
		nexts = new ArrayList<>();
	}

	public List<IModelComponent> getNext() {
		return nexts;
	}
	
	public void setNext(List<IModelComponent> next) {
		nexts = next;
	}

	public void addNext(IModelComponent next) {
		nexts.add(next);
	}
	
	public void removeNext(IModelComponent next) {
		nexts.remove(next);
	}
	
	public void putToNexts(ZgloszenieGeneric zgl) {
		for (IModelComponent next : nexts) {
			next.processing(zgl);
		}
	}
}