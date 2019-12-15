//package declaration
package ch.nolix.common.rasterAPI;

//interface
/**
 * A {@link TopLeftPositioned} knows its top left position.
 * The top left position of a {@link TopLeftPositioned} is discrete.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 20
 */
public interface TopLeftPositioned {
	
	//method declaration
	/**
	 * @return the top left x-position of the current {@link TopLeftPositioned}.
	 */
	public abstract int getXPosition();
	
	//method declaration
	/**
	 * @return the top left y-position of the current {@link TopLeftPositioned}.
	 */
	public abstract int getYPosition();
}
