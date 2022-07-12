//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Titleble;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Refreshable;
import ch.nolix.coreapi.geometryapi.griduniversalapi.Rectangular;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;

//interface
/**
 * @author Silvan Wyss
 * @date 2019-08-01
 * @param <G> is the type of a {@link IGUI}.
 */
public interface IGUI<G extends IGUI<G>>
extends GroupCloseable, IResizableInputTaker, Rectangular, Refreshable, Titleble<G> {
	
	//method declaration
	/**
	 * @return the {@link IFrontEndReader} of the current {@link IGUI}.
	 */
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	/**
	 * @return the cursor icon on the current {@link IGUI}.
	 */
	CursorIcon getCursorIcon();
	
	//method declaration
	/**
	 * @return the x-position of the cursor on the view area of the current {@link IGUI}.
	 */
	int getCursorXPositionOnViewArea();
	
	//method declaration
	/**
	 * @return the y-position of the cursor on the view area of the current {@link IGUI}.
	 */
	int getCursorYPositionOnViewArea();
	
	//method declaration
	/**
	 * @return the icon of the current {@link IGUI}.
	 */
	IImage getIcon();
	
	//method declaration
	/**
	 * @return the height of the view area of the current {@link IGUI}.
	 */
	int getViewAreaHeight();
	
	//method declaration
	/**
	 * @return the width of the view area of the current {@link IGUI}.
	 */
	int getViewAreaWidth();
	
	//method declaration
	/**
	 * A root {@link IGUI} is a {@link IGUI} that not contained in another {@link IGUI}.
	 * 
	 * @return true if the current {@link IGUI} is a root {@link IGUI}.
	 */
	boolean isRootGUI();
	
	//method declaration
	/**
	 * 
	 * @return true if the current {@link IGUI} is visible.
	 */
	boolean isVisible();
	
	//method
	/**
	 * Lets the current {@link IGUI} note a resize.
	 * The size of the view area of the current {@link IGUI} will be the size of the view area of the given pGUI.
	 * 
	 * @param pGUI
	 */
	void noteResizeFrom(IGUI<?> pGUI);
	
	//method declaration
	/**
	 * @return the {@link IFrontEndWriter} of the current {@link IGUI}.
	 */
	IFrontEndWriter onFrontEnd();
	
	//method
	/**
	 * Sets the icon of the current{@link IGUI}.
	 * 
	 * @param icon
	 * @return the current{@link IGUI}.
	 */
	G setIcon(final IImage icon);
}
