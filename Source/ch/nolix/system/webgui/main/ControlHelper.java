/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.html.htmlmodel.HtmlAttribute;
import ch.nolix.baseapi.html.htmlcatalog.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlAttribute;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * Of the {@link ControlHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class ControlHelper {
  /**
   * Prevents that an instance of the {@link ControlHelper} can be created.
   */
  private ControlHelper() {
  }

  /**
   * @param control
   * @return a new id Html attribute for the given control.
   * @throws RuntimeException if the given control is null.
   */
  public static IHtmlAttribute createIdHtmlAttributeForControl(final IControl<?, ?> control) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, control.getInternalId());
  }
}
