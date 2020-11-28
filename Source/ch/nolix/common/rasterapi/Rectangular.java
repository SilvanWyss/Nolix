//package declaration
package ch.nolix.common.rasterapi;

//interface
/**
 * A {@link Rectangular} has a discrete, non-negative width and a height.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 40
 */
public interface Rectangular {
	
	//method
	/**
	 * @return the diagonal of the current {@link Rectangular}.
	 */
	default double getDiagonal() {
		return Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2));
	}
	
	//method declaration
	/**
	 * @return the height of the current {@link Rectangular}.
	 */
	int getHeight();
	
	//method
	/**
	 * @return the perimeter of the current {@link Rectangular}.
	 */
	default int getPerimeter() {
		return (2 * (getWidth() + getHeight()));
	}
	
	//method declaration
	/**
	 * @return the width of the current {@link Rectangular}.
	 */
	int getWidth();
	
	//method
	/**
	 * @return true if the current {@link Rectangular} is quadratic.
	 */
	default boolean isQuadratic() {
		return (getWidth() == getHeight());
	}
}
