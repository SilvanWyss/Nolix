/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.horizontalstack;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackHtmlBuilder implements IControlHtmlBuilder<IHorizontalStack> {
  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlElement createHtmlElementForControl(final IHorizontalStack horizontalStack) {
    return //
    HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      HorizontalStackHtmlBuilderHelper.createHtmlElementsForChildControlsOfHorizontalStack(horizontalStack));
  }
}
