//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class ValidationLabelHtmlBuilder implements IControlHtmlBuilder<IValidationLabel> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final IValidationLabel control) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(CONTROL_HELPER.createIdHtmlAttributeForControl(control)),
			getHtmlDivInnerTextForControl(control)
		);
	}
	
	//method
	private String getHtmlDivInnerTextForControl(final IValidationLabel control) {
		
		if (control.isEmpty()) {
			return "\u2800";
		}
		
		return (control.getError().getMessage() + "\u2800");
	}
}
