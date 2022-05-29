//package declaration
package ch.nolix.systemapi.guiapi.baseapi.colorapi;

//Java imports
import java.awt.Color;

import ch.nolix.systemapi.elementuniversalapi.mainuniversalapi.Specified;

//interface
public interface IColor extends Specified {
	
	//method declaration
	/**
	 * @return the hexadecimal value of the current {@link Color} always with alpha value.
	 */
	String getHexadecimalValueAlwaysWithAlphaValue();
	
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
