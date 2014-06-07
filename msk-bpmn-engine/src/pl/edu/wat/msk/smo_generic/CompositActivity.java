package pl.edu.wat.msk.smo_generic;

import java.util.List;

import pl.edu.wat.msk.elements.HavePrevNext;

/**
 * Aktywność złożona.
 * @since 07.06.2014
 * @author Mariusz Kielan
 *
 */
public class CompositActivity extends HavePrevNext {
	private HavePrevNext firstComponent;
	private HavePrevNext lastComponent;
	private List<HavePrevNext> subComponents;
	
	public CompositActivity(String id, HavePrevNext first, HavePrevNext last, List<HavePrevNext> all) {
		this.id = id;
		this.firstComponent = first;
		this.lastComponent = last;
		subComponents = all;
		
		setDependency();
	}
	
	@Override
	public void processing(ZgloszenieGeneric zgl, String id) {
		firstComponent.processing(zgl, this.getId());
	}

	public HavePrevNext getFirstComponent() {
		return firstComponent;
	}

	public HavePrevNext getLastComponent() {
		return lastComponent;
	}

	public List<HavePrevNext> getSubComponents() {
		return subComponents;
	}

	public void setFirstComponent(HavePrevNext firstComponent) {
		this.firstComponent = firstComponent;
	}

	public void setLastComponent(HavePrevNext lastComponent) {
		this.lastComponent = lastComponent;
	}

	public void setSubComponents(List<HavePrevNext> subComponents) {
		this.subComponents = subComponents;
	}
	
	/**
	 * Ustawienie następników całej aktywności złożonej
	 * dla ostatniego komponentu w modelu.
	 */
	private void setDependency() {
		lastComponent.setNext(this.getNext());
	}
}
