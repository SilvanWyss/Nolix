//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.layerhelper.LayerHelper;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerHTMLCreator {
	
	//static attribute
	public static final LayerHTMLCreator INSTANCE = new LayerHTMLCreator();
	
	//constructor
	private LayerHTMLCreator() {}
	
	//method
	public IHtmlElement<?, ?> getHTMLElementForLayer(final ILayer<?> layer) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			getHTMLAttributesForLayer(layer),
			getHTMLChildElementsForLayer(layer)
		);
	}
	
	//method
	private IContainer<IHtmlAttribute> getHTMLAttributesForLayer(final ILayer<?> layer) {
		return ImmutableList.withElement(LayerHelper.INSTANCE.createIdHTMLAttributeForLayer(layer));
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> getHTMLChildElementsForLayer(final ILayer<?> layer) {
		
		if (layer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElement(getContentHTMLElementForLayer(layer));
	}
	
	//method
	private IHtmlElement<?, ?> getContentHTMLElementForLayer(final ILayer<?> layer) {
		return layer.getOriRootControl().toHTMLElement();
	}
}
