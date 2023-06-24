//package declaration
package ch.nolix.system.webgui.controlhelper;

//own imports
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class ControlHelper {
	
	//static attribute
	public static final ControlHelper INSTANCE = new ControlHelper();
	
	//constructor
	private ControlHelper() {}
	
	//method
	public HtmlAttribute createIdHtmlAttributeForControl(final IControl<?, ?> control) {
		return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, control.getInternalId());
	}
}
