/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.floatcontainer;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class FloatContainerHtmlBuilder implements IControlHtmlBuilder<FloatContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlElement createHtmlElementForControl(final FloatContainer floatContainer) {
    return //
    HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      FloatContainerHtmlBuilderHelper.createHtmlElementsForChildControlsOfFloatContainer(floatContainer));
  }
}
