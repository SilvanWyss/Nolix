/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.floatcontainer;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class FloatContainerHtmlBuilder implements IControlHtmlBuilder<FloatContainer> {
  @Override
  public HtmlElement createHtmlElementForControl(final FloatContainer floatContainer) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      createHtmlElementsForChildControlsOfFloatContainer(floatContainer));
  }

  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfFloatContainer(
    final FloatContainer floatContainer) {
    return floatContainer.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(
      HtmlElementTypeCatalog.DIV,
      childControl.getHtml());
  }
}
