/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.horizontalstack;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.systemapi.gui.box.VerticalContentAlignment;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStack;

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
