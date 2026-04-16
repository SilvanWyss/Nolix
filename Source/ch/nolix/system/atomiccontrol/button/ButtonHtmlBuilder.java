/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.button;

import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.atomiccontrol.button.IButton;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class ButtonHtmlBuilder implements IControlHtmlBuilder<IButton> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final IButton button) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.BUTTON,
      button.getText());
  }
}
