//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class LabelHtmlBuilder implements IControlHtmlBuilder<ILabel> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final ILabel control) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElement(CONTROL_HELPER.createIdHtmlAttributeForControl(control)),
			control.getText()
		);
	}
}
