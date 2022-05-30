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
	 * @return the active {@link IWidgetLook} of the current {@link IWidget}.
	 */
	WL getRefActiveLook();
	
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
