package ch.nolix.systemapi.graphic.color;

import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.graphic.imageproperty.Alignment;

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
  Alignment getDirection();
}
