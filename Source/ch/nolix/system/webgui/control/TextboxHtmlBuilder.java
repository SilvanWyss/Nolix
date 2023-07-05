//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
		return
		HtmlElement.withTypeAndAttributes(
			HtmlElementTypeCatalogue.INPUT,
			ImmutableList.withElements(
				ControlHelper.INSTANCE.createIdHtmlAttributeForControl(textbox),
				HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.VALUE, textbox.getText())
			)
		);
	}
}
