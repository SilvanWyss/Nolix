/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.verticalstack;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webcontainercontrol.verticalstack.IVerticalStack;
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
