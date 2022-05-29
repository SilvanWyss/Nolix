//package declaration
package ch.nolix.systemapi.guiapi.widgetapi;

//own imports
import ch.nolix.core.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.core.requestuniversalapi.EnablingRequestable;
import ch.nolix.core.requestuniversalapi.ExpansionRequestable;
import ch.nolix.core.skilluniversalapi.Recalculable;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
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
	 * Sets the current {@link Widget} focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	W setFocused();
}
