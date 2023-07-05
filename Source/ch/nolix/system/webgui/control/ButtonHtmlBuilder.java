//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class ButtonHtmlBuilder implements IControlHtmlBuilder<Button> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final Button button) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.BUTTON,
			ImmutableList.withElement(
				CONTROL_HELPER.createIdHtmlAttributeForControl(button)
			),
			button.getText()
		);
	}
}
