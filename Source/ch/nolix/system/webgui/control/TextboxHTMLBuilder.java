//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class TextboxHTMLBuilder implements IControlHTMLBuilder<ITextbox> {
	
	//static attribute
	public static final TextboxHTMLBuilder INSTANCE = new TextboxHTMLBuilder();
	
	//constructor
	private TextboxHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final ITextbox textbox) {
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
