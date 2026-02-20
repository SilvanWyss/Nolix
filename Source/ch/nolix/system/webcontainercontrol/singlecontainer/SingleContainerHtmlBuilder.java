/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.singlecontainer;

import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;
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
