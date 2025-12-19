package ch.nolix.system.webcontainercontrol.horizontalstack;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackHtmlBuilder implements IControlHtmlBuilder<IHorizontalStack> {
  @Override
  public HtmlElement createHtmlElementForControl(final IHorizontalStack horizontalStack) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      createHtmlElementsForChildControlsOfHorizontalStack(horizontalStack));
  }

  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfHorizontalStack(
    final IHorizontalStack horizontalStack) {
    return horizontalStack.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
