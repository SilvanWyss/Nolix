//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Recalculable;
import ch.nolix.coreapi.functionapi.requestuniversalapi.EnablingRequestable;
import ch.nolix.coreapi.functionapi.requestuniversalapi.ExpansionRequestable;
import ch.nolix.coreapi.geometryapi.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.mainapi.IInputActionManager;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//interface
/**
 * A {@link IWidget} is an element on a GUI.
 * 
 * @author Silvan Wyss
 * @date 2022-05-29
 * @param <W> is the type of a {@link IWidget}.
 * @param <WL> is the type of the {@link IWidgetLook} of a {@link IWidget}.
 */
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
	
	//method
	/**
	 * @return the x-position of the content area of the current {@link IWidget} on the current {@link IWidget}.
	 */
	int getContentAreaXPosition();
	
	//method
	/**
	 * @return the x-position of the content area of the current {@link IWidget} on the current {@link IWidget}.
	 */
	int getContentAreaYPosition();
	
	//method declaration
	/**
	 * @return the {@link CursorIcon} of the current {@link IWidget}.
	 */
	CursorIcon getCursorIcon();
	
	//method declaration
	/**
	 * @return the {@link IWidgetGUI} the current {@link IWidget} belongs to, directly or indirectly.
	 * @throws RuntimeException if the current {@link IWidget} does not belong to a {@link IWidgetGUI}.
	 */
	IWidgetGUI<?> getParentGUI();
	
	//method declaration
	/**
	 * @return the {@link ILayer} the current {@link IWidget} belongs to.
	 * @throws RuntimeException if the current {@link IWidget} does not belong to a {@link ILayer}.
	 */
	ILayer<?> getParentLayer();
	
	//method declaration
	/**
	 * @return the active {@link IWidgetLook} of the current {@link IWidget}.
	 */
	WL getRefActiveLook();
	
	//method declaration
	/** 
	 * @return the {@link IWidget}s of the current {@link IWidget} that have to be painted.
	 */
	LinkedList<IWidget<?, ?>> getRefWidgetsForPainting();
	
	//method declaration
	/**
	 * @return the x-position of the current {@link IWidget} on
	 * the view area of the parent {@link IWidgetGUI} of the current {@link IWidget}.
	 */
	int getXPositionOnGUIViewArea();
	
	//method declaration
	/**
	 * @return the y-position of the current {@link IWidget} on
	 * the view area of the parent {@link IWidgetGUI} of the current {@link IWidget}.
	 */
	int getYPositionOnGUIViewArea();
	
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
	
	//method declaration
	/**
	 * Lets the current {@link IWidget} note a mouse move.
	 */
	void _noteMouseMove();
	
	//method declaration
	/**
	 * Sets the position of the cursor on the current {@link IWidget}.
	 * 
	 * @param cursorXPosition
	 * @param cursorYPosition
	 */
	void _setCursorPosition(int cursorXPosition, int cursorYPosition);
	
	//method declaration
	/**
	 * Sets the {@link ILayer} the current {@link IWidget} will belong to.
	 * 
	 * @param parentLayer
	 * @throws RuntimeException if the given parentLayer is null.
	 * @throws RuntimeException if
	 * the current {@link IWidget} belongs already to a {@link IWidget} or to another {@link ILayer}.
	 */
	void _setParentLayer(ILayer<?> parentLayer);
	
	//method declaration
	/**
	 * Sets the {@link IWidget} the current {@link IWidget} will belong to.
	 * 
	 * @param parentWidget
	 * @throws RuntimeException if the given parentWidget is null.
	 * @throws RuntimeException if
	 * the current {@link IWidget} belongs already to another {@link IWidget} or to a {@link ILayer}.
	 */
	void _setParentWidget(IWidget<?, ?> parentWidget);
}
