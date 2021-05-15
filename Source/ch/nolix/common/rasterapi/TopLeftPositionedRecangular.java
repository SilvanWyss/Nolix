//package declaration
package ch.nolix.common.rasterapi;

//interface
/**
 * A {@link TopLeftPositionedRecangular} contains (!) its top left position.
 * 
 * Example of a {@link TopLeftPositionedRecangular} with the top-left-position = (3, 2), width = 4, height = 3
 *   0  1  2  3  4  5  6  7  8
 * 0 -------------------------
 * 1 -------------------------
 * 2 ---------XXXXXXXXXXXX----
 * 3 ---------XXXXXXXXXXXX----
 * 4 ---------XXXXXXXXXXXX----
 * 5 -------------------------
 * 6 -------------------------
 * 7 -------------------------
 * 8 -------------------------
 * 
 * @author Silvan Wyss
 * @date 2019-07-28
 * @lines 50
 */
public interface TopLeftPositionedRecangular extends Rectangular, TopLeftPositioned {
	
	//method
	/**
	 * @param xPosition
	 * @param yPosition
	 * @return true if the current {@link TopLeftPositionedRecangular}
	 * contains the point with the given xPosition and yPosition.
	 */
	default boolean containsPoint(final int xPosition, final int yPosition) {
		return
		getXPosition() <= xPosition
		&& getYPosition() <= yPosition
		&& getXPosition() + getWidth() > xPosition
		&& getYPosition() + getHeight() > yPosition;
	}
	
	//method
	/**
	 * @param relativeXPosition
	 * @param relativeYPosition
	 * @return true if the current {@link TopLeftPositionedRecangular}
	 * contains the point with the given relativeXPosition and relativeYPosition.
	 */
	default boolean containsPointRelatively(final int relativeXPosition, final int relativeYPosition) {
		return
		relativeXPosition >= 0
		&& relativeYPosition >= 0
		&& relativeXPosition < getWidth()
		&& relativeYPosition < getHeight();
	}
}
