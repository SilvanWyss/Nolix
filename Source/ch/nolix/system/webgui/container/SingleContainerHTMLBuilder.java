//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class SingleContainerHTMLBuilder implements IControlHtmlBuilder<SingleContainer> {
	
	//static attribute
	public static final SingleContainerHTMLBuilder INSTANCE = new SingleContainerHTMLBuilder();
	
	//constructor
	private SingleContainerHTMLBuilder() {}
	
	//method
	@Override
	public IHtmlElement<?, ?> createHTMLElementForControl(final SingleContainer control) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			createHTMLElementsForChildControlsOfSingleContainer(control)
		);
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> createHTMLElementsForChildControlsOfSingleContainer(
		final SingleContainer singleContainer
	) {
		
		if (singleContainer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(singleContainer.getOriControl().toHTMLElement());
	}
}
