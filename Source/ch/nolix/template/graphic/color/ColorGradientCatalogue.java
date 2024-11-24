package ch.nolix.template.graphic.color;

import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.ColorGradient;
import ch.nolix.systemapi.guiapi.canvasapi.DirectionInCanvas;

public final class ColorGradientCatalogue {

  public static final ColorGradient VERTICAL_BLACK_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(DirectionInCanvas.VERTICAL, Color.BLACK, Color.WHITE);

  public static final ColorGradient VERTICAL_GREY_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(DirectionInCanvas.VERTICAL, Color.BLACK, Color.GREY);

  public static final ColorGradient VERTICAL_RED_WHITE_COLOR_GRADIENT = //
  ColorGradient.withDirectionAndColors(DirectionInCanvas.VERTICAL, Color.RED, Color.WHITE);

  private ColorGradientCatalogue() {
  }
}
