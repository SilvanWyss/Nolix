//package declaration
package ch.nolix.element.baseguiapi;

import ch.nolix.common.rasterapi.TopLeftPositionedRecangular;
import ch.nolix.element.input.IInputTaker;

//interface
/**
 * A {@link IViewArea} is the area of a {@link IBaseGUI} where is painted on.
 * A {@link IViewArea} excludes a probable frame.
 * A {@link IViewArea} returns data only and does not have a mutation method.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 30
 */
interface IViewArea extends IInputTaker, TopLeftPositionedRecangular {
	
	//method declaration
	/**
	 * @return the x-position of the cursor on the of the current {@link IViewArea}.
	 */
	int getCursorXPosition();
	
	//method declaration
	/**
	 * @return the y-position of the cursor on the current {@link IViewArea}.
	 */
	int getCursorYPosition();
	
	//method
	/**
	 * @return true if the current {@link IViewArea} is under the cursor.
	 */
	default boolean isUnderCursor() {
		return containsPoint(getCursorXPosition(), getCursorYPosition());
	}
}
