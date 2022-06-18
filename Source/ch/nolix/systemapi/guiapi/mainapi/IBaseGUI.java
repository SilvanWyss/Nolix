//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi.Titleble;
import ch.nolix.core.griduniversalapi.Rectangular;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.core.skilluniversalapi.Refreshable;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;

//interface
/**
 * @author Silvan Wyss
 * @date 2019-08-01
 * @param <G> is the type of a {@link IBaseGUI}.
 */
public interface IBaseGUI<G extends IBaseGUI<G>>
extends GroupCloseable, IResizableInputTaker, Rectangular, Refreshable, Titleble<G> {
	
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
	 * @return the icon of the current {@link IBaseGUI}.
	 */
	IImage getIcon();
	
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
	void noteResizeFrom(IBaseGUI<?> pGUI);
	
	//method declaration
	/**
	 * @return the {@link IFrontEndWriter} of the current {@link IBaseGUI}.
	 */
	IFrontEndWriter onFrontEnd();
	
	//method
	/**
	 * Sets the icon of the current{@link IBaseGUI}.
	 * 
	 * @param icon
	 * @return the current{@link IBaseGUI}.
	 */
	G setIcon(final IImage icon);
}
