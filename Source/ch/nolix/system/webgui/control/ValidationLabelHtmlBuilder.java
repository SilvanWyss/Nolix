//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class ValidationLabelHtmlBuilder implements IControlHtmlBuilder<ValidationLabel> {
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final ValidationLabel control) {
		return
		HtmlElement.withTypeAndAttributesAndInnerText(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHtmlAttributeForControl(control)),
			getHtmlDivInnerTextForControl(control)
		);
	}
	
	//method
	private String getHtmlDivInnerTextForControl(final ValidationLabel control) {
		
		if (control.isEmpty()) {
			return "\u2800";
		}
		
		return (control.getError().getMessage() + "\u2800");
	}
}
