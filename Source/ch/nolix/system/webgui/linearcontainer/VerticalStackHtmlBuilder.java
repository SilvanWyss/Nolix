package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.linearcontainer.IVerticalStack;
import ch.nolix.systemapi.webgui.main.IControl;

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
