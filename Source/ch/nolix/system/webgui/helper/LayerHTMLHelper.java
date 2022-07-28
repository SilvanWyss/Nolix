//package declaration
package ch.nolix.system.webgui.helper;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.html.HTMLAttribute;
import ch.nolix.core.document.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.document.html.HTMLElement;
import ch.nolix.core.document.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerHTMLHelper {
	
	//static attribute
	public static final LayerHTMLHelper INSTANCE = new LayerHTMLHelper();
	
	//constructor
	private LayerHTMLHelper() {}
	
	public IContainer<IHTMLAttribute> getHTMLAttributesOfLayer(final ILayer<?> layer) {
		return ImmutableList.withElements(getZIndexHTMLAttributeOfLayer(layer));
	}
	
	//method
	public IContainer<IHTMLElement<?, ?>> getHTMLChildElementsOfLayer(final ILayer<?> layer) {
		
		if (layer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(layer.getRefRootControl().toHTMLElement());
	}
	
	//method
	public IHTMLElement<?, ?> getHTMLElementOfLayer(final ILayer<?> layer) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			getHTMLAttributesOfLayer(layer),
			getHTMLChildElementsOfLayer(layer)
		);
	}
	
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
