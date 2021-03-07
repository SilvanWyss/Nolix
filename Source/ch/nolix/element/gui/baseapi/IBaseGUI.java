//package declaration
package ch.nolix.element.gui.baseapi;

import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Titleble;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.skillapi.Refreshable;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.input.IResizableInputTaker;

//interface
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 80
 * @param <G> is the type of a {@link IBaseGUI}.
 */
public interface IBaseGUI<G extends IBaseGUI<G>>
extends ICloseableElement, IResizableInputTaker, Rectangular, Refreshable, Titleble<G> {
	
	//method declaration
	/**
	 * @return the {@link IFrontEndReader} of the current {@link IBaseGUI}.
	 */
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	/**
	 * @return the cursor icon on the current {@link IBaseGUI}.
	 */
	CursorIcon getCursorIcon();
	
	//method declaration
	/**
	 * @return the x-position of the cursor on the view area of the current {@link IBaseGUI}.
	 */
	int getCursorXPositionOnViewArea();
	
	//method declaration
	/**
	 * @return the y-position of the cursor on the view area of the current {@link IBaseGUI}.
	 */
	int getCursorYPositionOnViewArea();
	
	//method declaration
	/**
	 * @return the height of the view area of the current {@link IBaseGUI}.
	 */
	int getViewAreaHeight();
	
	//method declaration
	/**
	 * @return the width of the view area of the current {@link IBaseGUI}.
	 */
	int getViewAreaWidth();
	
	//method declaration
	/**
	 * A root {@link IBaseGUI} is a {@link IBaseGUI} that not contained in another {@link IBaseGUI}.
	 * 
	 * @return true if the current {@link IBaseGUI} is a root {@link IBaseGUI}.
	 */
	boolean isRootGUI();
	
	//method declaration
	/**
	 * 
	 * @return true if the current {@link IBaseGUI} is visible.
	 */
	boolean isVisible();
	
	//method
	/**
	 * Lets the current {@link IBaseGUI} note a resize.
	 * The size of the view area of the current {@link IBaseGUI} will be the size of the view area of the given pGUI.
	 * 
	 * @param pGUI
	 */
	default void noteResizeFrom(final IBaseGUI<?> pGUI) {
		noteResize(pGUI.getViewAreaWidth(), pGUI.getViewAreaHeight());
	}
	
	//method declaration
	/**
	 * @return the {@link IFrontEndWriter} of the current {@link IBaseGUI}.
	 */
	IFrontEndWriter onFrontEnd();
}
