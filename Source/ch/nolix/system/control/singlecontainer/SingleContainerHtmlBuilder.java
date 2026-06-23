/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.singlecontainer;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.control.singlecontainer.ISingleContainer;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class SingleContainerHtmlBuilder implements IControlHtmlBuilder<ISingleContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final ISingleContainer control) {
    return //
    HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
      SingleContainerHtmlBuilderHelper.createHtmlElementsForChildControlsOfSingleContainer(control));
  }
}
