//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class HorizontalStackHtmlBuilder implements IControlHtmlBuilder<IHorizontalStack> {

  //method
  @Override
  public HtmlElement createHtmlElementForControl(final IHorizontalStack horizontalStack) {
    return HtmlElement.withTypeAndChildElements(
        HtmlElementTypeCatalogue.DIV,
        createHtmlElementsForChildControlsOfHorizontalStack(horizontalStack));
  }

  //method
  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfHorizontalStack(
      final IHorizontalStack horizontalStack) {
    return horizontalStack.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  //method
  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.getHtml());
  }
}
