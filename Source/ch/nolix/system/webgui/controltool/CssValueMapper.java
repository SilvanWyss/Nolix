/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controltool;

import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 */
public final class CssValueMapper {
  private CssValueMapper() {
  }

  public static String mapColorToCssValue(final IColor color) {
    return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
  }

  public static String mapRelativeOrAbsoluteIntToCssValue(
    final IAbsoluteOrRelativeInt absoluteOrRelativeInt,
    final String relativeIntCssUnit) {
    if (absoluteOrRelativeInt.isAbsolute()) {
      return absoluteOrRelativeInt.getAbsoluteValue() + "px";
    }

    return (100 * absoluteOrRelativeInt.getPercentage()) + relativeIntCssUnit;
  }
}
