//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class TextHTMLBuilder implements IControlHTMLBuilder<Text> {
	
	//static attribute
	public static final TextHTMLBuilder INSTANCE = new TextHTMLBuilder();
	
	//constructor
	private TextHTMLBuilder() {}
	
	//method
	@Override
	public IHTMLElement<?, ?> createHTMLElementForControl(final Text control) {
		return
		HTMLElement.withTypeAndAttributesAndInnerText(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			control.getText()
		);
	}
}
