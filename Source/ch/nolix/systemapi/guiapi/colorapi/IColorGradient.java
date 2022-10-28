//package declaration
package ch.nolix.systemapi.guiapi.colorapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

//interface
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
