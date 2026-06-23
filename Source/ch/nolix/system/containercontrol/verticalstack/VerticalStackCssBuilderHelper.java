/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.verticalstack;

import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.baseapi.web.csscatalog.CssPropertyNameCatalog;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStack;
import ch.nolix.systemapi.gui.box.HorizontalContentAlignment;

/**
 * @author Silvan Wyss
 */
public final class VerticalStackCssBuilderHelper {
  private VerticalStackCssBuilderHelper() {
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
