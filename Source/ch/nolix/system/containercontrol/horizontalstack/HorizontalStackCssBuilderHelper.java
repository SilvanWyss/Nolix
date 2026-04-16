/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.horizontalstack;

import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.baseapi.web.css.CssPropertyNameCatalog;
import ch.nolix.systemapi.containercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemapi.gui.box.VerticalContentAlignment;

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
