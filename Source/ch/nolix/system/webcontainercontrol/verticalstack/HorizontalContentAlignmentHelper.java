/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.verticalstack;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.systemapi.gui.box.HorizontalContentAlignment;
import ch.nolix.systemapi.webcontainercontrol.verticalstack.IVerticalStack;

/**
 * @author Silvan Wyss
 */
public final class HorizontalContentAlignmentHelper {
  private HorizontalContentAlignmentHelper() {
  }

  public static CssProperty createCssPropertyForContentAlignmentOfVerticalStack(final IVerticalStack verticalStack) {
    final var contentAlignment = verticalStack.getContentAlignment();

    return createCssPropertyForContentAlignment(contentAlignment);
  }

  private static CssProperty createCssPropertyForContentAlignment(final HorizontalContentAlignment contentAlignment) {
    return //
    switch (contentAlignment) {
      case LEFT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "start");
      case CENTER ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "center");
      case RIGHT ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "end");
    };
  }
}
