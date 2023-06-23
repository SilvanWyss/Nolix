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
	
	//static attribute
	public static final ButtonHtmlBuilder INSTANCE = new ButtonHtmlBuilder();
	
	//constructor
	private ButtonHtmlBuilder() {}
	
	//method
	@Override
	public HtmlElement createHTMLElementForControl(final Button button) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.BUTTON,
			ImmutableList.withElement(
				ControlHelper.INSTANCE.createIdHTMLAttributeForControl(button)
			),
			button.getText()
		);
	}
}
