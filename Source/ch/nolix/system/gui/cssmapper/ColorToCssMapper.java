package ch.nolix.system.gui.cssmapper;

import java.util.Locale;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.cssmapper.IColorToCssMapper;

/**
 * A {@link CornerShadowToCssMapper} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public final class ColorToCssMapper implements IColorToCssMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public String mapColorToCssValueCode(final IColor color) {

    if (color.hasFullAlphaValue()) {
      return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
    }

    return //
    String.format(
      Locale.ENGLISH,
      "rgba(%d, %d, %d, %f)",
      color.getRedValue(),
      color.getGreenValue(),
      color.getBlueValue(),
      color.getAlphaPercentage());
  }
}
