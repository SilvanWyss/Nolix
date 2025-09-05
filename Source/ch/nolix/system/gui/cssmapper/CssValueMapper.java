package ch.nolix.system.gui.cssmapper;

import java.util.Locale;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.cssmapper.ICssValueMapper;

/**
 * A {@link CssValueMapper} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public final class CssValueMapper implements ICssValueMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public String mapColorToCssValue(final IColor color) {
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
