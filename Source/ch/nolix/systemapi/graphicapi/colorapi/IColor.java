package ch.nolix.systemapi.graphicapi.colorapi;

import ch.nolix.systemapi.elementapi.baseapi.IElement;

/**
 * A {@link IColor} represents a true color with an alpha value. A true color
 * consists of a blue, green and red value that are integers in [0, 255]. So, a
 * {@link IColor} consists of a blue, green, red and alpha value that are
 * integers in [0, 255].
 * 
 * @author Silvan
 * @version 2022-05-28
 */
public interface IColor extends IElement {

  /**
   * @return the alpha value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getAlphaPercentage();

  /**
   * @return the alpha value of the current {@link IColor}.
   */
  int getAlphaValue();

  /**
   * @return the blue value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getBluePercentage();

  /**
   * @return the blue value of the current {@link IColor}.
   */
  int getBlueValue();

  /**
   * @return the color name or a hexadecimal {@link String} representation of the
   *         current {@link IColor}.
   */
  String getColorNameOrHexadecimalString();

  /**
   * @return the green value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getGreenPercentage();

  /**
   * @return the green value of the current {@link IColor}.
   */
  int getGreenValue();

  /**
   * The inverted {@link IColor} of a {@link IColor} has the same alpha value.
   * 
   * @return the inverted {@link IColor} of the current {@link IColor}.
   */
  IColor getInvertedColor();

  /**
   * @return the red value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getRedPercentage();

  /**
   * @return the red value of the current {@link IColor}.
   */
  int getRedValue();

  /**
   * @return true if the current {@link IColor} has a full alpha value.
   */
  boolean hasFullAlphaValue();

  /**
   * @return true if the current {@link IColor} has a full blue value.
   */
  boolean hasFullBlueValue();

  /**
   * @return true if the current {@link IColor} has a full green value.
   */
  boolean hasFullGreenValue();

  /**
   * @return true if the current {@link IColor} has a full red value.
   */
  boolean hasFullRedValue();

  /**
   * @return an integer representation of the current {@link IColor} with the
   *         schema alpha-red-green-blue.
   */
  int toAlphaRedGreenBlueInt();

  /**
   * @return a hexadecimal {@link String} representation of the current
   *         {@link IColor}.
   */
  String toHexadecimalString();

  /**
   * @return a hexadecimal with alpha value {@link String} representation of the
   *         current {@link IColor}.
   */
  String toHexadecimalStringWithAlphaValue();

  /**
   * @return a long representation of the current {@link IColor}.
   */
  long toLong();

  /**
   * @param alphaValue
   * @return a new {@link IColor} from the current {@link IColor} with the given
   *         alphaValue.
   */
  IColor withAlphaValue(int alphaValue);

  /**
   * @param floatingPointAlphaValue
   * @return a new {@link IColor} from the current {@link IColor} with the given
   *         floatingPointAlphaValue.
   */
  IColor withFloatingPointAlphaValue(double floatingPointAlphaValue);

  /**
   * @return a new {@link IColor} from the current {@link IColor} with a full
   *         alpha value.
   */
  IColor withFullAlphaValue();
}
