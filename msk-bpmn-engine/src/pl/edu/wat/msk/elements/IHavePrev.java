package pl.edu.wat.msk.elements;

import java.util.List;

/**
 * 
 * @since 24.05.2014, 19:50
 * Generated by AgroUML
 *
 */
public interface IHavePrev {

	public List<IModelComponent> getPrev();
	
	public void setPrev(List<IModelComponent> prev);

	public void addPrev(IModelComponent prev);
	
	public void removePrev(IModelComponent prev);
}