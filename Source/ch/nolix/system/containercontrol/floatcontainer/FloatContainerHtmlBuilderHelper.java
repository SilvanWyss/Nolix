/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.floatcontainer;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class FloatContainerHtmlBuilderHelper {
  private FloatContainerHtmlBuilderHelper() {
  }

  public static ExtendedIterable<HtmlElement> createHtmlElementsForChildControlsOfFloatContainer(
    final FloatContainer floatContainer) {
    return //
    floatContainer.getStoredChildControls().to(FloatContainerHtmlBuilderHelper::createHtmlElementsForChildControl);
  }

  private static HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
