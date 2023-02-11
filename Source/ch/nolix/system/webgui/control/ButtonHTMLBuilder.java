//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class ButtonHTMLBuilder implements IControlHTMLBuilder<Button> {
	
	//static attribute
	public static final ButtonHTMLBuilder INSTANCE = new ButtonHTMLBuilder();
	
	//constructor
	private ButtonHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final Button button) {
		return
		HTMLElement.withTypeAndAttributesAndInnerText(
			HTMLElementTypeCatalogue.BUTTON,
			ImmutableList.withElement(
				ControlHelper.INSTANCE.createIdHTMLAttributeForControl(button)
			),
			button.getText()
		);
	}
}
