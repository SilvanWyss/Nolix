/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.tabcontainer;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.containercontrol.tabcontainer.ITabContainer;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class TabContainerHtmlBuilder implements IControlHtmlBuilder<ITabContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final ITabContainer control) {
    final var rootVerticalStack = control.internalGetStoredRootVerticalStack();
    final var rootVerticalStackHtml = rootVerticalStack.getHtml();

    return HtmlElement.withTypeAndChildElements(HtmlElementTypeCatalog.DIV, rootVerticalStackHtml);
  }
}
