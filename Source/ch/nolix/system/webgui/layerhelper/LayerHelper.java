//package declaration
package ch.nolix.system.webgui.layerhelper;

//own imports
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerHelper {
	
	//static attribute
	public static final LayerHelper INSTANCE = new LayerHelper();
	
	//constructor
	private LayerHelper() {}
	
	//method
	public HTMLAttribute createIdHTMLAttributeForLayer(final ILayer<?> layer) {
		return HTMLAttribute.withNameAndValue(HTMLAttributeNameCatalogue.ID, layer.getInternalId());
	}
}
