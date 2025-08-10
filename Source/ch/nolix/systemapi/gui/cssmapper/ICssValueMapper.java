package ch.nolix.systemapi.gui.cssmapper;

import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public interface ICssValueMapper {

  /**
   * @param color
   * @return the CSS value from the given color.
   * @throws RuntimeException if the given color is null.
   */
  String mapColorToCssValue(IColor color);
}
