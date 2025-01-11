package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class VerticalStackHtmlBuilder implements IControlHtmlBuilder<IVerticalStack> {

  @Override
  public HtmlElement createHtmlElementForControl(final IVerticalStack verticalStack) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      createHtmlElementsForChildControlsOfVerticalStack(verticalStack));
  }

  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfVerticalStack(
    final IVerticalStack verticalStack) {
    return verticalStack.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
