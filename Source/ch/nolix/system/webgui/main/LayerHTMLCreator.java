//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.core.web.html.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.webgui.layerhelper.LayerHelper;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerHTMLCreator {
	
	//static attribute
	public static final LayerHTMLCreator INSTANCE = new LayerHTMLCreator();
	
	//constructor
	private LayerHTMLCreator() {}
	
	//method
	public IHTMLElement<?, ?> getHTMLElementForLayer(final ILayer<?> layer) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			getHTMLAttributesForLayer(layer),
			getHTMLChildElementsForLayer(layer)
		);
	}
	
	//method
	private IContainer<IHTMLAttribute> getHTMLAttributesForLayer(final ILayer<?> layer) {
		return ImmutableList.withElements(LayerHelper.INSTANCE.createIdHTMLAttributeForLayer(layer));
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> getHTMLChildElementsForLayer(final ILayer<?> layer) {
		
		if (layer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(getContentHTMLElementForLayer(layer));
	}
	
	//method
	private IHTMLElement<?, ?> getContentHTMLElementForLayer(final ILayer<?> layer) {
		return layer.getRefRootControl().toHTMLElement();
	}
}
