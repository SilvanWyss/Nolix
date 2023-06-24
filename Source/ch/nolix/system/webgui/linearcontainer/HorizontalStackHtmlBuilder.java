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
public final class HorizontalStackHtmlBuilder implements IControlHtmlBuilder<IHorizontalStack> {
	
	//static attribute
	public static final HorizontalStackHtmlBuilder INSTANCE = new HorizontalStackHtmlBuilder();
	
	//constructor
	private HorizontalStackHtmlBuilder() {}
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final IHorizontalStack horizontalStack) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElements(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(horizontalStack)),
			createHtmlElementsForChildControlsOfHorizontalStack(horizontalStack)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHtmlElementsForChildControlsOfHorizontalStack(
		final IHorizontalStack horizontalStack
	) {
		return horizontalStack.getOriChildControls().to(this::createHtmlElementsForChildControl);
	}
	
	//method
	private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
		return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.toHTMLElement());
	}
}
