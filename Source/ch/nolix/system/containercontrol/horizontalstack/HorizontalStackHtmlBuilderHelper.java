/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.horizontalstack;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.containercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackHtmlBuilderHelper {
  private HorizontalStackHtmlBuilderHelper() {
  }

  public static ExtendedIterable<HtmlElement> createHtmlElementsForChildControlsOfHorizontalStack(
    final IHorizontalStack horizontalStack) {
    return //
    horizontalStack.getStoredChildControls().to(HorizontalStackHtmlBuilderHelper::createHtmlElementsForChildControl);
  }

  private static HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
