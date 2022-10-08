//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;

//class
public final class TextboxHTMLCreator {
	
	//static attribute
	public static final TextboxHTMLCreator INSTANCE = new TextboxHTMLCreator();
	
	//constructor
	private TextboxHTMLCreator() {}
	
	//method
	public HTMLElement createHTMLElementForTextbox(final ITextbox<?, ?> textbox) {
		//TODO: Implement.
		return null;
	}
}
