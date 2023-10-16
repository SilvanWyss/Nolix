//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class VerticalStackHtmlBuilder implements IControlHtmlBuilder<IVerticalStack> {

  //method
  @Override
  public HtmlElement createHtmlElementForControl(final IVerticalStack verticalStack) {
    return HtmlElement.withTypeAndChildElements(
        HtmlElementTypeCatalogue.DIV,
        createHtmlElementsForChildControlsOfVerticalStack(verticalStack));
  }

  //method
  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfVerticalStack(
      final IVerticalStack verticalStack) {
    return verticalStack.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  //method
  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.getHtml());
  }
}
