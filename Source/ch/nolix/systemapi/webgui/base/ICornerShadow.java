/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.base;

import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.guiproperty.Corner;
import ch.nolix.systemapi.gui.guiproperty.Location;

/**
 * @author Silvan Wyss
 */
public interface ICornerShadow extends Element {
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
