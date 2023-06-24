//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.layerhelper.LayerHelper;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerHtmlCreator {
	
	//static attribute
	public static final LayerHtmlCreator INSTANCE = new LayerHtmlCreator();
	
	//constructor
	private LayerHtmlCreator() {}
	
	//method
	public IHtmlElement<?, ?> getHtmlElementForLayer(final ILayer<?> layer) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			getHtmlAttributesForLayer(layer),
			getHtmlChildElementsForLayer(layer)
		);
	}
	
	//method
	private IContainer<IHtmlAttribute> getHtmlAttributesForLayer(final ILayer<?> layer) {
		return ImmutableList.withElement(LayerHelper.INSTANCE.createIdHtmlAttributeForLayer(layer));
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> getHtmlChildElementsForLayer(final ILayer<?> layer) {
		
		if (layer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElement(getContentHtmlElementForLayer(layer));
	}
	
	//method
	private IHtmlElement<?, ?> getContentHtmlElementForLayer(final ILayer<?> layer) {
		return layer.getOriRootControl().toHtmlElement();
	}
}