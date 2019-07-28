//package declaration
package ch.nolix.core.rasterAPI;

//interface
/**
 * A {@link Rectangular} has a discrete, non-negative width and a height.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 30
 */
public interface Rectangular {
	
	//default method
	/**
	 * @return the diagonal of the current {@link Rectangular}.
	 */
	public default double getDiagonal() {
		return Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2));
	}
	
	//abstract method
	/**
	 * @return the height of the current {@link Rectangular}.
	 */
	public abstract int getHeight();
	
	//abstract method
	/**
	 * @return the width of the current {@link Rectangular}.
	 */
	public abstract int getWidth();
}
