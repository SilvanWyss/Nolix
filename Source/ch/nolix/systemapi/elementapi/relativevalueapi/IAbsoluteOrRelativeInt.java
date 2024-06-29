//package declaration
package ch.nolix.systemapi.elementapi.relativevalueapi;

//own imports
import ch.nolix.systemapi.elementapi.baseapi.IElement;

//interface
/**
 * A {@link IAbsoluteOrRelativeInt} stores either an integer or a percentage.
 * 
 * @author Silvan Wyss
 * @version 2022-10-15
 */
public interface IAbsoluteOrRelativeInt extends IElement {

  //method declaration
  /**
   * @return the absolute value of the current {@link IAbsoluteOrRelativeInt}.
   * @throws RuntimeException if the current {@link IAbsoluteOrRelativeInt} is not
   *                          absolute.
   */
  int getAbsoluteValue();

  //method declaration
  /**
   * @return the percentage of the current {@link IAbsoluteOrRelativeInt}.
   * @throws RuntimeException if the current {@link IAbsoluteOrRelativeInt} is not
   *                          relative.
   */
  double getPercentage();

  //method declaration
  /**
   * @param hundredPercentValue
   * @return the value of the current {@link IAbsoluteOrRelativeInt} relative to
   *         the given hundredPercentValue.
   */
  int getValueRelativeToHundredPercentValue(int hundredPercentValue);

  //method declaration
  /**
   * @return true if the current {@link IAbsoluteOrRelativeInt} is absolute.
   */
  boolean isAbsolute();

  //method declaration
  /**
   * @return true if the current {@link IAbsoluteOrRelativeInt} is positive.
   */
  boolean isPositive();

  //method declaration
  /**
   * @return true if the current {@link IAbsoluteOrRelativeInt} is relative.
   */
  boolean isRelative();
}
