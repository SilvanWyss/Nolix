//package declaration
package ch.nolix.systemapi.graphicapi.colorapi;

import ch.nolix.systemapi.elementapi.mainapi.Specified;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-05-28
 */
public interface IColorGradient extends Specified {
	
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
	DirectionInRectangle getDirection();
}
