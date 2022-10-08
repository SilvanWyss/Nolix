//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;

//class
public final class TextboxHTMLCreator {
	
	//static attribute
	public static final TextboxHTMLCreator INSTANCE = new TextboxHTMLCreator();
	
	//constructor
	private TextboxHTMLCreator() {}
	
	//method
	public HTMLElement createHTMLElementForTextbox(final ITextbox<?, ?> textbox) {
		return
		HTMLElement.withTypeAndAttributes(
			HTMLElementTypeCatalogue.INPUT,
			ImmutableList.withElements(
				ControlHelper.INSTANCE.createIdHTMLAttributeForControl(textbox),
				HTMLAttribute.withNameAndValue(HTMLAttributeNameCatalogue.VALUE, textbox.getText())
			)
		);
	}
}
