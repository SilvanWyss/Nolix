/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.singlecontainer;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.containercontrol.singlecontainer.ISingleContainer;
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
