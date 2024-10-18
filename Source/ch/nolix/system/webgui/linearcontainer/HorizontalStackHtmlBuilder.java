package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class HorizontalStackHtmlBuilder implements IControlHtmlBuilder<IHorizontalStack> {

  @Override
  public HtmlElement createHtmlElementForControl(final IHorizontalStack horizontalStack) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalogue.DIV,
      createHtmlElementsForChildControlsOfHorizontalStack(horizontalStack));
  }

  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfHorizontalStack(
    final IHorizontalStack horizontalStack) {
    return horizontalStack.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalogue.DIV, childControl.getHtml());
  }
}
