/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.relativevalue;

import ch.nolix.systemapi.element.base.IElement;

/**
 * A {@link IAbsoluteOrRelativeInt} stores either an integer or a percentage.
 * 
 * @author Silvan Wyss
 */
public interface IAbsoluteOrRelativeInt extends IElement {
  /**
   * @return the absolute value of the current {@link IAbsoluteOrRelativeInt}.
   * @throws RuntimeException if the current {@link IAbsoluteOrRelativeInt} is not
   *                          absolute.
   */
  int getAbsoluteValue();

  /**
   * @return the percentage of the current {@link IAbsoluteOrRelativeInt}.
   * @throws RuntimeException if the current {@link IAbsoluteOrRelativeInt} is not
   *                          relative.
   */
  double getPercentage();

  /**
   * @param hundredPercentValue
   * @return the value of the current {@link IAbsoluteOrRelativeInt} relative to
   *         the given hundredPercentValue.
   */
  int getValueRelativeToHundredPercentValue(int hundredPercentValue);

  /**
   * @return true if the current {@link IAbsoluteOrRelativeInt} is absolute, false
   *         otherwise.
   */
  boolean isAbsolute();

  /**
   * @return true if the current {@link IAbsoluteOrRelativeInt} is positive, false
   *         otherwise.
   */
  boolean isPositive();

  /**
   * @return true if the current {@link IAbsoluteOrRelativeInt} is relative, false
   *         otherwise.
   */
  boolean isRelative();
}
