package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class FloatContainerHtmlBuilder implements IControlHtmlBuilder<FloatContainer> {

  @Override
  public HtmlElement createHtmlElementForControl(final FloatContainer floatContainer) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalogue.DIV,
      createHtmlElementsForChildControlsOfFloatContainer(floatContainer));
  }

  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfFloatContainer(
    final FloatContainer floatContainer) {
    return floatContainer.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(
      HtmlElementTypeCatalogue.DIV,
      childControl.getHtml());
  }
}
