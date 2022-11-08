//package declaration
package ch.nolix.systemapi.graphicapi.colorapi;

//Java imports
import java.awt.Color;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
/**
 * A {@link IColor} represents a true color with an alpha value.
 * A true color consists of a blue, green and red value that are integers in [0, 255].
 * So, a {@link IColor} consists of a blue, green, red and alpha value that are integers in [0, 255].
 * 
 * @author Silvan
 * @date 2022-05-28
 */
public interface IColor extends Specified {
	
	//method declaration
	/**
	 * @return the alpha value of the current {@link IColor}.
	 */
	int getAlphaValue();
	
	//method declaration
	/**
	 * @return the blue value of the current {@link IColor}.
	 */
	int getBlueValue();
	
	//method declaration
	/**
	 * @return the green value of the current {@link IColor}.
	 */
	int getGreenValue();
	
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
	 * @return an integer that represents the current {@link IColor} in the schema alpha-red-green-blue.
	 */
	int toAlphaRedGreenBlueValue();
	
	//method declaration
	/**
	 * @return a hexadecimal {@link String} representation of the current {@link IColor}.
	 */
	String toHexadecimalString();
	
	//method declaration
	/**
	 * @return a hexadecimal with alpha value {@link String} representation of the current {@link IColor}.
	 */
	String toHexadecimalStringWithAlphaValue();
	
	//method declaration
	/**
	 * @return a new {@link java.awt.Color} from the current {@link IColor}.
	 */
	Color toSwingColor();
	
	//method declaration
	/**
	 * @param floatingPointAlphaValue
	 * @return a new {@link IColor} from the current {@link IColor} with the given floatingPointAlphaValue.
	 */
	IColor withFloatingPointAlphaValue(final double floatingPointAlphaValue);
	
	//method declaration
	/**
	 * @return a new {@link IColor} from the current {@link IColor} with a full alpha value.
	 */
	IColor withFullAlphaValue();
}
