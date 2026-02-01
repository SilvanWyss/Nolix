/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.horizontalstack;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackHtmlBuilderHelper {
  private HorizontalStackHtmlBuilderHelper() {
  }

  public static IContainer<HtmlElement> createHtmlElementsForChildControlsOfHorizontalStack(
    final IHorizontalStack horizontalStack) {
    return //
    horizontalStack.getStoredChildControls().to(HorizontalStackHtmlBuilderHelper::createHtmlElementsForChildControl);
  }

  private static HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
