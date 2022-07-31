//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Titleble;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Refreshable;
import ch.nolix.coreapi.geometryapi.griduniversalapi.Rectangular;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;

//interface
/**
 * @author Silvan Wyss
 * @date 2019-08-01
 * @param <G> is the type of a {@link IExtendedGUI}.
 */
public interface IExtendedGUI<G extends IExtendedGUI<G>>
extends GroupCloseable, IGUI<G>, IResizableInputTaker, Rectangular, Refreshable, Titleble<G> {
	
	//method declaration
	/**
	 * @return the cursor icon on the current {@link IExtendedGUI}.
	 */
	CursorIcon getCursorIcon();
	
	//method declaration
	/**
	 * @return the x-position of the cursor on the view area of the current {@link IExtendedGUI}.
	 */
	int getCursorXPositionOnViewArea();
	
	//method declaration
	/**
	 * @return the y-position of the cursor on the view area of the current {@link IExtendedGUI}.
	 */
	int getCursorYPositionOnViewArea();
	
	//method declaration
	/**
	 * @return the height of the view area of the current {@link IExtendedGUI}.
	 */
	int getViewAreaHeight();
	
	//method declaration
	/**
	 * @return the width of the view area of the current {@link IExtendedGUI}.
	 */
	int getViewAreaWidth();
	
	//method declaration
	/**
	 * 
	 * @return true if the current {@link IExtendedGUI} is visible.
	 */
	boolean isVisible();
	
	//method
	/**
	 * Lets the current {@link IExtendedGUI} note a resize.
	 * The size of the view area of the current {@link IExtendedGUI} will be the size of the view area of the given pGUI.
	 * 
	 * @param pGUI
	 */
	void noteResizeFrom(IExtendedGUI<?> pGUI);
}
