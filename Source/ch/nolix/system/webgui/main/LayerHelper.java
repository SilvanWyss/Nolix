//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.document.html.HTMLAttribute;
import ch.nolix.core.document.html.HTMLAttributeNameCatalogue;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
final class LayerHelper {
	
	//static attribute
	public static final LayerHelper INSTANCE = new LayerHelper();
	
	//constructor
	private LayerHelper() {}
	
	//method
	public int getHTMLZIndexOfLayer(final ILayer<?> layer) {
		
		if (!layer.belongsToGUI()) {
			return 0;
		}
		
		return layer.getRefParentGUI().getRefLayers().getIndexOfFirstOccurrenceOf(layer);
	}
	
	//method
	public IHTMLAttribute getZIndexHTMLAttributeOfLayer(final ILayer<?> layer) {
		return
		HTMLAttribute.withNameAndValue(
			HTMLAttributeNameCatalogue.Z_INDEX,
			String.valueOf(getHTMLZIndexOfLayer(layer))
		);
	}
}
