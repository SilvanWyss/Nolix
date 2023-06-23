//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class HorizontalStackHTMLBuilder implements IControlHTMLBuilder<IHorizontalStack> {
	
	//static attribute
	public static final HorizontalStackHTMLBuilder INSTANCE = new HorizontalStackHTMLBuilder();
	
	//constructor
	private HorizontalStackHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final IHorizontalStack horizontalStack) {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(horizontalStack)),
			createHTMLElementsForChildControlsOfHorizontalStack(horizontalStack)
		);
	}
	
	//method
	private IContainer<HTMLElement> createHTMLElementsForChildControlsOfHorizontalStack(
		final IHorizontalStack horizontalStack
	) {
		return horizontalStack.getOriChildControls().to(this::createHTMLElementsForChildControl);
	}
	
	//method
	private HTMLElement createHTMLElementsForChildControl(final IControl<?, ?> childControl) {
		return HTMLElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.toHTMLElement());
	}
}
