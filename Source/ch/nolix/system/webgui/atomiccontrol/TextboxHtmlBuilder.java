//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;

//class
public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
		return
		HtmlElement.withTypeAndAttributes(
			HtmlElementTypeCatalogue.INPUT,
			ImmutableList.withElement(
				CONTROL_HELPER.createIdHtmlAttributeForControl(textbox),
				HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.VALUE, textbox.getText())
			)
		);
	}
}
