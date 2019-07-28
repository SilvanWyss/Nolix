//package declaration
package ch.nolix.core.rasterAPI;

//interface
/**
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 20
 */
public interface TopLeftPositionedRecangular extends Rectangular, TopLeftPositioned {
	
	//default method
	/**
	 * @param xPosition
	 * @param yPosition
	 * @return true if the current {@link TopLeftPositionedRecangular}
	 * contains the point with the given xPosition and yPosition.
	 */
	public default boolean containsPoint(final int xPosition, final int yPosition) {
		return
		getXPosition() <= xPosition
		&& getYPosition() <= yPosition
		&& getXPosition() + getWidth() > xPosition
		&& getYPosition() + getHeight() > yPosition;
	}
}
