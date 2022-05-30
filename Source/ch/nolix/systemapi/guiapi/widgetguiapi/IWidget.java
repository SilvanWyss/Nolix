//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

//own imports
import ch.nolix.core.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.core.requestuniversalapi.EnablingRequestable;
import ch.nolix.core.requestuniversalapi.ExpansionRequestable;
import ch.nolix.core.skilluniversalapi.Recalculable;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.baseapi.CursorIcon;
import ch.nolix.systemapi.guiapi.baseapi.IInputActionManager;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//interface
public interface IWidget<
	W extends IWidget<W, WL>,
	WL extends IWidgetLook<WL>
>
extends
EnablingRequestable,
ExpansionRequestable,
IConfigurableElement<W>,
IInputActionManager<W>,
IInputTaker,
Recalculable,
TopLeftPositionedRecangular {
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} belongs to a {@link IWidgetGUI}, directly or indirectly.
	 */
	boolean belongsToGUI();
	
	//method declaration
	/**
	 * @return the {@link CursorIcon} of the current {@link IWidget}.
	 */
	CursorIcon getCursorIcon();
	
	//method declaration
	/**
	 * @return the {@link IWidgetGUI} the current {@link IWidget} belongs to, directly or indirectly.
	 * @throws Exception if the current {@link IWidget} does not belong to a {@link IWidgetGUI}.
	 */
	IWidgetGUI<?> getParentGUI();
	
	//method declaration
	/**
	 * @return the {@link ILayer} the current {@link IWidget} belongs to.
	 * @throws Exception if the current {@link IWidget} does not belong to a {@link ILayer}.
	 */
	ILayer<?> getParentLayer();
	
	//method declaration
	/**
	 * @return the active {@link IWidgetLook} of the current {@link IWidget}.
	 */
	WL getRefActiveLook();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} has a left mouse button press action.
	 */
	boolean hasLeftMouseButtonPressAction();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} has a left mouse button release action.
	 */
	boolean hasLeftMouseButtonReleaseAction();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} has a right mouse button press action.
	 */
	boolean hasRightMouseButtonPressAction();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} has a right mouse button release action.
	 */
	boolean hasRightMouseButtonReleaseAction();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} is focused.
	 */
	boolean isFocused();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} is hovered.
	 */
	boolean isHovered();
	
	//method declaration
	/**
	 * @return true if the current {@link IWidget} is under the cursor.
	 */
	boolean isUnderCursor();
	
	//method declaration
	/**
	 * Paints the current {@link IWidget} using
	 * the position of the current {@link IWidget} on its parent and the given painter.
	 * Will paint also the child {@link IWidget} of the current {@link IWidget}, that are chosen for painting.
	 * Ensures that the given painter has the same position at the end of the painting as the beginning.
	 * 
	 * @param painter
	 */
	void paint(final IPainter painter);
	
	//method declaration
	/**
	 * Sets the current {@link IWidget} collapsed.
	 * 
	 * @return the current {@link IWidget}.
	 */
	W setCollapsed();
	
	//method declaration
	/**
	 * Sets the current {@link IWidget} focused.
	 * 
	 * @return the current {@link IWidget}.
	 */
	W setFocused();
	
	//method declaration
	/**
	 * Sets the position of the current {@link IWidget} on its parent.
	 * 
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 */
	void setPositionOnParent(int xPositionOnParent, int yPositionOnParent);
}
