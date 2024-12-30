package ch.nolix.template.graphic.color;

import ch.nolix.system.graphic.color.ColorGradient;
import ch.nolix.system.graphic.color.X11ColorCatalogue;
import ch.nolix.systemapi.guiapi.canvasapi.DirectionInCanvas;

public final class ColorGradientCatalogue {

  public static final ColorGradient VERTICAL_BLACK_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(DirectionInCanvas.VERTICAL, X11ColorCatalogue.BLACK, X11ColorCatalogue.WHITE);

  public static final ColorGradient VERTICAL_GREY_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(DirectionInCanvas.VERTICAL, X11ColorCatalogue.BLACK, X11ColorCatalogue.GREY);

  public static final ColorGradient VERTICAL_RED_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(DirectionInCanvas.VERTICAL, X11ColorCatalogue.RED, X11ColorCatalogue.WHITE);

  private ColorGradientCatalogue() {
  }
}
