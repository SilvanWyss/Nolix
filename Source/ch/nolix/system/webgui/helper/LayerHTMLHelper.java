//package declaration
package ch.nolix.system.webgui.helper;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerHTMLHelper {
	
	//static attribute
	public static final LayerHTMLHelper INSTANCE = new LayerHTMLHelper();
	
	//constructor
	private LayerHTMLHelper() {}
	
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
	private IContainer<IHTMLAttribute> getHTMLAttributesOfLayer(final ILayer<?> layer) {
		return
		ImmutableList.withElements(
			HTMLAttribute.withNameAndValue(
				HTMLAttributeNameCatalogue.STYLE,
				"position: absolute; z-index: " + getHTMLZIndexOfLayer(layer)
			)
		);
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> getHTMLChildElementsOfLayer(final ILayer<?> layer) {
		
		if (layer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(layer.getRefRootControl().toHTMLElement());
	}
	
	//method
	private int getHTMLZIndexOfLayer(final ILayer<?> layer) {
		
		if (!layer.belongsToGUI()) {
			return 0;
		}
		
		return layer.getRefParentGUI().getRefLayers().getIndexOfFirstOccurrenceOf(layer);
	}
}
