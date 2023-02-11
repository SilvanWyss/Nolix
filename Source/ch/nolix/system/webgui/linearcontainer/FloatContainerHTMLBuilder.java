//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class FloatContainerHTMLBuilder implements IControlHTMLBuilder<FloatContainer> {
	
	//constant
	public static final String CHILD_CONTROL_CSS_CLASS_NAME = "floatContainerChild";
	
	//static attribute
	public static final FloatContainerHTMLBuilder INSTANCE = new FloatContainerHTMLBuilder();
	
	//constructor
	private FloatContainerHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final FloatContainer floatContainer) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElement(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(floatContainer)),
			createHTMLElementsForChildControlsOfFloatContainer(floatContainer)
		);
	}
	
	//method
	private IContainer<HTMLElement> createHTMLElementsForChildControlsOfFloatContainer(
		final FloatContainer floatContainer
	) {
		return floatContainer.getRefChildControls().to(this::createHTMLElementsForChildControl);
	}
	
	//method
	private HTMLElement createHTMLElementsForChildControl(final IControl<?, ?> childControl) {
		return
		HTMLElement.withTypeAndAttributesAndChildElement(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElement(
				HTMLAttribute.withNameAndValue(
					HTMLAttributeNameCatalogue.CLASS,
					CHILD_CONTROL_CSS_CLASS_NAME
				)
			),
			childControl.toHTMLElement()
		);
	}
}
