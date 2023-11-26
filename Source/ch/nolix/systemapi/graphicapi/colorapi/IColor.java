//package declaration
package ch.nolix.systemapi.graphicapi.colorapi;

//own imports
import ch.nolix.systemapi.elementapi.specificationapi.Specified;

//interface
/**
 * A {@link IColor} represents a true color with an alpha value. A true color
 * consists of a blue, green and red value that are integers in [0, 255]. So, a
 * {@link IColor} consists of a blue, green, red and alpha value that are
 * integers in [0, 255].
 * 
 * @author Silvan
 * @date 2022-05-28
 */
public interface IColor extends Specified {

  //method declaration
  /**
   * @return the alpha value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getAlphaPercentage();

  //method declaration
  /**
   * @return the alpha value of the current {@link IColor}.
   */
  int getAlphaValue();

  //method declaration
  /**
   * @return the blue value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getBluePercentage();

  //method declaration
  /**
   * @return the blue value of the current {@link IColor}.
   */
  int getBlueValue();

  //method declaration
  /**
   * @return the color name or a hexadecimal {@link String} representation of the
   *         current {@link IColor}.
   */
  String getColorNameOrHexadecimalString();

  //method declaration
  /**
   * @return the green value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getGreenPercentage();

  //method declaration
  /**
   * @return the green value of the current {@link IColor}.
   */
  int getGreenValue();

  //method declaration
  /**
   * The inverted {@link IColor} of a {@link IColor} has the same alpha value.
   * 
   * @return the inverted {@link IColor} of the current {@link IColor}.
   */
  IColor getInvertedColor();

  //method declaration
  /**
   * @return the red value of the current {@link IColor} as percentage. A
   *         percentage is a number in the range [0.0, 1.0].
   */
  double getRedPercentage();

  //method declaration
  /**
   * @return the red value of the current {@link IColor}.
   */
  int getRedValue();

  //method declaration
  /**
   * @return true if the current {@link IColor} has a full alpha value.
   */
  boolean hasFullAlphaValue();

  //method declaration
  /**
   * @return true if the current {@link IColor} has a full blue value.
   */
  boolean hasFullBlueValue();

  //method declaration
  /**
   * @return true if the current {@link IColor} has a full green value.
   */
  boolean hasFullGreenValue();

  //method declaration
  /**
   * @return true if the current {@link IColor} has a full red value.
   */
  boolean hasFullRedValue();

  //method declaration
  /**
   * @return an integer representation of the current {@link IColor} with the
   *         schema alpha-red-green-blue.
   */
  int toAlphaRedGreenBlueInt();

  //method declaration
  /**
   * @return a hexadecimal {@link String} representation of the current
   *         {@link IColor}.
   */
  String toHexadecimalString();

  //method declaration
  /**
   * @return a hexadecimal with alpha value {@link String} representation of the
   *         current {@link IColor}.
   */
  String toHexadecimalStringWithAlphaValue();

  //method declaration
  /**
   * @return a long representation of the current {@link IColor}.
   */
  long toLong();

  //method
  /**
   * @param alphaValue
   * @return a new {@link IColor} from the current {@link IColor} with the given
   *         alphaValue.
   */
  IColor withAlphaValue(int alphaValue);

  //method declaration
  /**
   * @param floatingPointAlphaValue
   * @return a new {@link IColor} from the current {@link IColor} with the given
   *         floatingPointAlphaValue.
   */
  IColor withFloatingPointAlphaValue(double floatingPointAlphaValue);

  //method declaration
  /**
   * @return a new {@link IColor} from the current {@link IColor} with a full
   *         alpha value.
   */
  IColor withFullAlphaValue();
}
