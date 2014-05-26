package pl.edu.wat.msk.elements;

import java.util.ArrayList;
import java.util.List;

import pl.edu.wat.msk.smo_generic.ZgloszenieGeneric;

/**
 * @author Mariusz Kielan
 * @since 24.05.2014, 19:50
 * @version 0.0.2
 */
public abstract class HavePrevNext extends ModelComponent implements IHaveNext, IHavePrev {

	protected List<IModelComponent> nexts;
	
	protected List<IModelComponent> prevs;
	
	
	public HavePrevNext() {
		nexts = new ArrayList<>();
		prevs = new ArrayList<>();
	}

	public List<IModelComponent> getPrev() {
		return prevs;
	}
	
	public void setPrev(List<IModelComponent> prev) {
		this.prevs = prev;
	}

	public void addPrev(IModelComponent prev) {
		this.prevs.add(prev);
	}
	
	public void removePrev(IModelComponent prev) {
		this.prevs.remove(prev);
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

	/**
	 * Może być przysłaniana w miarę potrzeb w każdej podklasie.
	 * @see pl.edu.wat.msk.elements.IHaveNext#putToNexts(pl.edu.wat.msk.Notification)
	 */
	public void putToNexts(ZgloszenieGeneric zgl) {
		for (IModelComponent next : nexts) {
			next.processing(zgl);
		}
	}
}