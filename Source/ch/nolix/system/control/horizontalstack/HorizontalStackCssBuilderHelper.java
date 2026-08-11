/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.horizontalstack;

import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.baseapi.web.csscatalog.CssPropertyNameCatalog;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.gui.guiproperty.VerticalContentAlignment;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackCssBuilderHelper {
  private HorizontalStackCssBuilderHelper() {
  }

  public static CssProperty createCssPropertyForContentAlignmentOfHorizontalStack(
    final IHorizontalStack horizontalStack) {
    final var contentAlignment = horizontalStack.getContentAlignment();

    return createCssPropertyForContentAlignment(contentAlignment);
  }

  private static CssProperty createCssPropertyForContentAlignment(final VerticalContentAlignment contentAlignment) {
    return switch (contentAlignment) {
      case TOP ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "start");
      case CENTER ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "center");
      case BOTTOM ->
        CssProperty.withNameAndValue(CssPropertyNameCatalog.ALIGN_ITEMS, "end");
    };
  }
}
