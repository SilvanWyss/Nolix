//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class HorizontalStackHTMLBuilder implements IControlHtmlBuilder<IHorizontalStack> {
	
	//static attribute
	public static final HorizontalStackHTMLBuilder INSTANCE = new HorizontalStackHTMLBuilder();
	
	//constructor
	private HorizontalStackHTMLBuilder() {}
	
	//method
	@Override
	public HtmlElement createHTMLElementForControl(final IHorizontalStack horizontalStack) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(horizontalStack)),
			createHTMLElementsForChildControlsOfHorizontalStack(horizontalStack)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHTMLElementsForChildControlsOfHorizontalStack(
		final IHorizontalStack horizontalStack
	) {
		return horizontalStack.getOriChildControls().to(this::createHTMLElementsForChildControl);
	}
	
	//method
	private HtmlElement createHTMLElementsForChildControl(final IControl<?, ?> childControl) {
		return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.toHTMLElement());
	}
}
