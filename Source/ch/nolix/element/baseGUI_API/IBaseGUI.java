//package declaration
package ch.nolix.element.baseGUI_API;

import ch.nolix.common.closeableelement.ICloseableElement;
import ch.nolix.common.mutableattributeapi.Titleble;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.skillapi.Refreshable;
import ch.nolix.element.input.IResizableInputTaker;

//interface
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 50
 * @param <G> The type of a {@link IBaseGUI}.
 */
public interface IBaseGUI<G extends IBaseGUI<G>>
extends ICloseableElement, IResizableInputTaker, Rectangular, Refreshable, Titleble<G> {
	
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
}
