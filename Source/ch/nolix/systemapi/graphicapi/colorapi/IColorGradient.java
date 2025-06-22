package ch.nolix.systemapi.graphicapi.colorapi;

import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.graphicapi.imageproperty.Alignment;

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
