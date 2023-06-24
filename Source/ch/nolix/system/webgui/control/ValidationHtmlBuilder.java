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
public final class ValidationHtmlBuilder implements IControlHtmlBuilder<ValidationLabel> {
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final ValidationLabel control) {
		
		if (control.isEmpty()) {
			return
			HtmlElement.withTypeAndAttributes(
				HtmlElementTypeCatalogue.DIV,
				ImmutableList.withElements(ControlHelper.INSTANCE.createIdHtmlAttributeForControl(control))
			);
		}
		
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHtmlAttributeForControl(control)),
			control.getError().getMessage()
		);
	}
}
