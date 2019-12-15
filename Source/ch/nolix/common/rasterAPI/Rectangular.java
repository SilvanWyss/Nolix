//package declaration
package ch.nolix.common.rasterAPI;

//interface
/**
 * A {@link Rectangular} has a discrete, non-negative width and a height.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 30
 */
public interface Rectangular {
	
	//method
	/**
	 * @return the diagonal of the current {@link Rectangular}.
	 */
	public default double getDiagonal() {
		return Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2));
	}
	
	//method declaration
	/**
	 * @return the height of the current {@link Rectangular}.
	 */
	public abstract int getHeight();
	
	//method declaration
	/**
	 * @return the width of the current {@link Rectangular}.
	 */
	public abstract int getWidth();
}
