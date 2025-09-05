package ch.nolix.systemapi.gui.colorgradient;

import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.box.Direction;

/**
 * @author Silvan Wyss
 * @version 2022-05-28
 */
public interface IColorGradient extends IElement {
  /**
   * @return the color1 of the current {@link IColorGradient}.
   */
  IColor getColor1();

  /**
   * @return the color2 of the current {@link IColorGradient}.
   */
  IColor getColor2();

  /**
   * @return the direction of the current {@link IColorGradient}.
   */
  Direction getDirection();
}
