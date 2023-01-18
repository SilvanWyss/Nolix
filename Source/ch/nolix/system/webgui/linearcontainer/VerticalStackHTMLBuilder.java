//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class VerticalStackHTMLBuilder implements IControlHTMLBuilder<VerticalStack> {
	
	//static attribute
	public static final VerticalStackHTMLBuilder INSTANCE = new VerticalStackHTMLBuilder();
	
	//constructor
	private VerticalStackHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final VerticalStack verticalStack) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HTMLElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(verticalStack)),
			createHTMLElementsForChildControlsOfVerticalStack(verticalStack)
		);
	}
	
	//method
	private IContainer<HTMLElement> createHTMLElementsForChildControlsOfVerticalStack(final VerticalStack verticalStack) {
		return verticalStack.getRefChildControls().to(this::createHTMLElementsForChildControl);
	}
	
	//method
	private HTMLElement createHTMLElementsForChildControl(final IControl<?, ?> childControl) {
		return HTMLElement.withTypeAndChildElement(HTMLElementTypeCatalogue.DIV, childControl.toHTMLElement());
	}
}
