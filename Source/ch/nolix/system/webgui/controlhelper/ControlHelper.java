//package declaration
package ch.nolix.system.webgui.controlhelper;

//own imports
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class ControlHelper {
	
	//static attribute
	public static final ControlHelper INSTANCE = new ControlHelper();
	
	//method
	public HTMLAttribute createIdHTMLAttributeForControl(final IControl<?, ?> control) {
		return HTMLAttribute.withNameAndValue(HTMLAttributeNameCatalogue.ID, control.getFixedId());
	}
}
