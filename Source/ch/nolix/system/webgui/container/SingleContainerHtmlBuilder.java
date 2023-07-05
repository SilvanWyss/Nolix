//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class SingleContainerHtmlBuilder implements IControlHtmlBuilder<ISingleContainer> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public IHtmlElement<?, ?> createHtmlElementForControl(final ISingleContainer control) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(CONTROL_HELPER.createIdHtmlAttributeForControl(control)),
			createHtmlElementsForChildControlsOfSingleContainer(control)
		);
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> createHtmlElementsForChildControlsOfSingleContainer(
		final ISingleContainer singleContainer
	) {
		
		if (singleContainer.isEmpty()) {
			return new ImmutableList<>();
		}
		
		return ImmutableList.withElements(singleContainer.getOriControl().toHtmlElement());
	}
}
