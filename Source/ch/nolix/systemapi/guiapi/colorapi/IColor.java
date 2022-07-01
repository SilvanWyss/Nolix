//package declaration
package ch.nolix.systemapi.guiapi.colorapi;

//Java imports
import java.awt.Color;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
public interface IColor extends Specified {
	
	//method declaration
	/**
	 * @return the hexadecimal value of the current {@link Color} always with alpha value.
	 */
	String getHexadecimalValueAlwaysWithAlphaValue();
	
	//method declaration
	/**
	 * @return true if the current {@link IColor} has a full alpha value.
	 */
	boolean hasFullAlphaValue();
	
	//method declaration
	/**
	 * @return an integer that represents the current {@link Color} in the schema alpha-red-green-blue.
	 */
	int toAlphaRedGreenBlueValue();
	
	//method declaration
	/**
	 * @return a new {@link java.awt.Color} from the current {@link IColor}.
	 */
	Color toSwingColor();
	
	//method declaration
	/**
	 * @param alphaValue
	 * @return a new {@link IColor} from the current {@link Color} with the given alphaValue.
	 */
	IColor withAlphaValue(final double alphaValue);
}
