//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class VerticalStackHtmlBuilder implements IControlHtmlBuilder<VerticalStack> {
	
	//static attribute
	public static final VerticalStackHtmlBuilder INSTANCE = new VerticalStackHtmlBuilder();
	
	//constructor
	private VerticalStackHtmlBuilder() {}
	
	//method
	@Override
	public HtmlElement createHTMLElementForControl(final VerticalStack verticalStack) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			ImmutableList.withElement(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(verticalStack)),
			createHTMLElementsForChildControlsOfVerticalStack(verticalStack)
		);
	}
	
	//method
	private IContainer<HtmlElement> createHTMLElementsForChildControlsOfVerticalStack(final VerticalStack verticalStack) {
		return verticalStack.getOriChildControls().to(this::createHTMLElementsForChildControl);
	}
	
	//method
	private HtmlElement createHTMLElementsForChildControl(final IControl<?, ?> childControl) {
		return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.toHTMLElement());
	}
}
