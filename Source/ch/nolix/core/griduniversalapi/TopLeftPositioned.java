//package declaration
package ch.nolix.core.griduniversalapi;

//own imports
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link TopLeftPositioned} knows its top left position.
 * The top left position of a {@link TopLeftPositioned} is discrete.
 * 
 * @author Silvan Wyss
 * @date 2019-07-28
 */
@AllowDefaultMethodsAsDesignPattern
public interface TopLeftPositioned {
	
	//method declaration
	/**
	 * @return the top left x-position of the current {@link TopLeftPositioned}.
	 */
	int getXPosition();
	
	//method declaration
	/**
	 * @return the top left y-position of the current {@link TopLeftPositioned}.
	 */
	int getYPosition();
}
