package pl.edu.wat.msk.elements;

import java.util.ArrayList;
import java.util.Vector;

import pl.edu.wat.msk.Notification;

/** 
 *  Aby uruchomić z?o?on? aktywność konieczne jest przechowywanie przez niego 
 *  komponentu źródła oraz celu.
 *  
 * @since 24.05.2014, 19:50
 * @author Mariusz Kielan
 *
 */
public class CompositeActivity extends HavePrevNext {

	private ArrayList<IModelComponent> modelComponents;
	
	public CompositeActivity() {
		modelComponents = new ArrayList<>();
	}
	
	@Override
	public Vector<ValidationMessage> validate() {
		Vector<ValidationMessage> msgs = new Vector<>();
		
		if(modelComponents.isEmpty()) {
			msgs.add(
				new ValidationMessage(
						"Brak komponentów", 
						"Aktywność złożona powinna zawierać conajmniej 2 komponenty: źródło i cel", 
						this
					)
			);
		}
		else {
			boolean containSource = false;
			boolean containDestination = false;
			
			for(int i = 0; 
				i < modelComponents.size() 
					&& containSource 
					&& !containDestination; 
				i++) {
			
				if(modelComponents.get(i).getClass() == Source.class)
					containSource = true;
				
				if(modelComponents.get(i).getClass() == Destination.class)
					containDestination = true;
			}
			
			if(!containSource) {
				msgs.add(
					new ValidationMessage(
						"Brak źródła", 
						"Aktywność złożona powinna zawierać źródło", 
						this
					)
				);
			}
			
			if(!containDestination) {
				msgs.add(
					new ValidationMessage(
						"Brak celu", 
						"Aktywność złożona powinna zawierać cel", 
						this
					)
				);
			}
		}
		
		return msgs;
	}
	
	/**
	 * Przekazanie zgłoszenia do źródła dla pod modelu.
	 */
	@Override
	public void processing(Notification notification) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<IModelComponent> getModelComponents() {
		return modelComponents;
	}
	
	public void setModelComponents(ArrayList<IModelComponent> modelComponents) {
		this.modelComponents = modelComponents;
	}

}