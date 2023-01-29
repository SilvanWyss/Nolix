//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class SingleContainerHTMLBuilder implements IControlHTMLBuilder<SingleContainer> {
	
	//static attribute
	public static final SingleContainerHTMLBuilder INSTANCE = new SingleContainerHTMLBuilder();
	
	//constructor
	private SingleContainerHTMLBuilder() {}
	
	//method
	@Override
	public IHTMLElement<?, ?> createHTMLElementForControl(final SingleContainer control) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(control)),
			createHTMLElementsForChildControlsOfSingleContainer(control)
		);
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> createHTMLElementsForChildControlsOfSingleContainer(
		final SingleContainer singleContainer
	) {
		
		if (singleContainer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(singleContainer.toHTMLElement());
	}
}
