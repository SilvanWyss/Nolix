/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.verticalstack;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStack;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class VerticalStackHtmlBuilder implements IControlHtmlBuilder<IVerticalStack> {
  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlElement createHtmlElementForControl(final IVerticalStack verticalStack) {
    return //
    HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      VerticalStackHtmlBuilderHelper.createHtmlElementsForChildControlsOfVerticalStack(verticalStack));
  }
}
