//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class LabelHTMLBuilder implements IControlHtmlBuilder<Label> {
	
	//static attribute
	public static final LabelHTMLBuilder INSTANCE = new LabelHTMLBuilder();
	
	//constructor
	private LabelHTMLBuilder() {}
	
	//method
	@Override
	public IHtmlElement<?, ?> createHTMLElementForControl(final Label control) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElement(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			control.getText()
		);
	}
}
