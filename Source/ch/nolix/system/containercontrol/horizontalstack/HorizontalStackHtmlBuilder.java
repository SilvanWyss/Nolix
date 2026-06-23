/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.horizontalstack;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.containercontrol.horizontalstack.IHorizontalStack;
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
