//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class ValidationHTMLBuilder implements IControlHTMLBuilder<ValidationLabel> {
	
	//method
	@Override
	public IHTMLElement<?, ?> createHTMLElementForControl(final ValidationLabel control) {
		
		if (control.isEmpty()) {
			return
			HTMLElement.withTypeAndAttributes(
				HTMLElementTypeCatalogue.DIV,
				ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control))
			);
		}
		
		return
		HTMLElement.withTypeAndAttributesAndInnerText(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			control.getError().getMessage()
		);
	}
}
