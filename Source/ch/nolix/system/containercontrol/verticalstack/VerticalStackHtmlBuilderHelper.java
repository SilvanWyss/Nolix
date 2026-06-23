/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.verticalstack;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStack;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class VerticalStackHtmlBuilderHelper {
  private VerticalStackHtmlBuilderHelper() {
  }

  public static ExtendedIterable<HtmlElement> createHtmlElementsForChildControlsOfVerticalStack(
    final IVerticalStack verticalStack) {
    return verticalStack.getStoredChildControls().to(VerticalStackHtmlBuilderHelper::createHtmlElementsForChildControl);
  }

  private static HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
