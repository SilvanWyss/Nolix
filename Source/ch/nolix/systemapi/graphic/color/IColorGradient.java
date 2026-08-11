/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.graphic.color;

import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.graphic.graphicproperty.Direction;

/**
 * @author Silvan Wyss
 */
public interface IColorGradient extends Element {
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
