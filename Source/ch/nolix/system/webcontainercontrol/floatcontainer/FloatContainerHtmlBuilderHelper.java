/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.floatcontainer;

import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class FloatContainerHtmlBuilderHelper {
  private FloatContainerHtmlBuilderHelper() {
  }

  public static IContainer<HtmlElement> createHtmlElementsForChildControlsOfFloatContainer(
    final FloatContainer floatContainer) {
    return //
    floatContainer.getStoredChildControls().to(FloatContainerHtmlBuilderHelper::createHtmlElementsForChildControl);
  }

  private static HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(HtmlElementTypeCatalog.DIV, childControl.getHtml());
  }
}
