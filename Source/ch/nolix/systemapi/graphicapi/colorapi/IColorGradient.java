//package declaration
package ch.nolix.systemapi.graphicapi.colorapi;

//own imports
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.guiapi.canvasapi.DirectionInCanvas;

//interface
/**
 * @author Silvan Wyss
 * @version 2022-05-28
 */
public interface IColorGradient extends IElement {

  //method declaration
  /**
   * @return the color1 of the current {@link IColorGradient}.
   */
  IColor getColor1();

  //method declaration
  /**
   * @return the color2 of the current {@link IColorGradient}.
   */
  IColor getColor2();

  //method declaration
  /**
   * @return the direction of the current {@link IColorGradient}.
   */
  DirectionInCanvas getDirection();
}
