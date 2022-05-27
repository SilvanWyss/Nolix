//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

import ch.nolix.core.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;

//interface
/**
 * A {@link IViewArea} is the area of a {@link IBaseGUI} where is painted on.
 * A {@link IViewArea} excludes a probable frame.
 * A {@link IViewArea} returns data only and does not have a mutation method.
 * 
 * @author Silvan Wyss
 * @date 2019-08-01
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
