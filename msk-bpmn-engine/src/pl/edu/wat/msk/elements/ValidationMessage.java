package pl.edu.wat.msk.elements;


/**
 * Wiadomość z walidacji komponentu.
 * 
 * @author Mariusz Kielan
 * @since 25.05.2014, 01:44
 *
 */
public class ValidationMessage {
	private String title;
	private String body;
	
	private IModelComponent component;
	
	public ValidationMessage() {
	}
	
	public ValidationMessage(String title, String body, IModelComponent component) {
		this.title = title;
		this.body = body;
		this.component = component;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public IModelComponent getComponent() {
		return component;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setComponent(IModelComponent component) {
		this.component = component;
	}
}
