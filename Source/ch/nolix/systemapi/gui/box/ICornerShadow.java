package ch.nolix.systemapi.gui.box;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.location.Location;

/**
 * @author Silvan Wyss
 * @version 2025-08-09
 */
public interface ICornerShadow {
  /**
   * @return the blur radius of the current {ICornerShadow}.
   */
  int getBlurRadius();

  /**
   * @return the color of the current {ICornerShadow}.
   */
  IColor getColor();

  /**
   * @return the corner of the current {ICornerShadow}.
   */
  Corner getCorner();

  /**
   * @return the location of the current {ICornerShadow}.
   */
  Location getLocation();

  /**
   * @return the side 1 thickness of the current {ICornerShadow}.
   */
  int getSide1Thickness();

  /**
   * @return the side 2 thickness of the current {ICornerShadow}.
   */
  int getSide2Thickness();
}
