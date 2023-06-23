//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class TextboxHTMLBuilder implements IControlHtmlBuilder<ITextbox> {
	
	//static attribute
	public static final TextboxHTMLBuilder INSTANCE = new TextboxHTMLBuilder();
	
	//constructor
	private TextboxHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final ITextbox textbox) {
		return
		HTMLElement.withTypeAndAttributes(
			HtmlElementTypeCatalogue.INPUT,
			ImmutableList.withElements(
				ControlHelper.INSTANCE.createIdHTMLAttributeForControl(textbox),
				HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.VALUE, textbox.getText())
			)
		);
	}
}
