//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class FloatContainerHtmlBuilder implements IControlHtmlBuilder<FloatContainer> {
	
	//constant
	public static final String CHILD_CONTROL_CSS_CLASS_NAME = "floatContainerChild";
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final FloatContainer floatContainer) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElement(CONTROL_HELPER.createIdHtmlAttributeForControl(floatContainer)),
			createHtmlElementsForChildControlsOfFloatContainer(floatContainer)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHtmlElementsForChildControlsOfFloatContainer(
		final FloatContainer floatContainer
	) {
		return floatContainer.getStoredChildControls().to(this::createHtmlElementsForChildControl);
	}
	
	//method
	private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
		return
		HtmlElement.withTypeAndAttributesAndChildElement(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElement(
				HtmlAttribute.withNameAndValue(
					HtmlAttributeNameCatalogue.CLASS,
					CHILD_CONTROL_CSS_CLASS_NAME
				)
			),
			childControl.getHtml()
		);
	}
}
