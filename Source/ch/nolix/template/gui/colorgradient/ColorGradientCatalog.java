/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.gui.colorgradient;

import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.colorgradient.ColorGradient;
import ch.nolix.systemapi.gui.box.Direction;

/**
 * @author Silvan Wyss
 */
public final class ColorGradientCatalog {
  public static final ColorGradient VERTICAL_BLACK_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(Direction.VERTICAL, X11ColorCatalog.BLACK, X11ColorCatalog.WHITE);

  public static final ColorGradient VERTICAL_GREY_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(Direction.VERTICAL, X11ColorCatalog.BLACK, X11ColorCatalog.GREY);

  public static final ColorGradient VERTICAL_RED_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(Direction.VERTICAL, X11ColorCatalog.RED, X11ColorCatalog.WHITE);

  private ColorGradientCatalog() {
  }
}
