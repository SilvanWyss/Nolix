//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Recalculable;
import ch.nolix.systemapi.elementapi.configurationapi.IStylableElement;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.mainapi.IOccupiableCanvasInputActionManager;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//interface
/**
 * A {@link ILayer} can have a root {@link IWidget}.
 * 
 * @author Silvan Wyss
 * @date 2022-05-29
 * @param <L> is the type of a {@link ILayer}.
 */
public interface ILayer<L extends ILayer<L>>
extends
Clearable,
IStylableElement<L>,
IOccupiableCanvasInputActionManager<L>,
IResizableInputTaker,
Recalculable {
	
	//method declaration
	/**
	 * @return true if the current {@link ILayer} allows to be configured.
	 */
	boolean allowesConfiguration();
	
	//method
	/**
	 * @return true if the current {@link ILayer} belongs to a {@link IWidgetGUI}.
	 */
	boolean belongsToGUI();
	
	//method declaration
	/**
	 * @return the {@link CursorIcon} of the current {@link ILayer}.
	 */
	CursorIcon getCursorIcon();
	
	//method declaration
	/**
	 * @return the x-position of the cursor on the current {@link ILayer}.
	 */
	int getCursorXPosition();
	
	//method declaration
	/**
	 * @return the y-position of the cursor on the current {@link ILayer}.
	 */
	int getCursorYPosition();
	
	//method declaration
	/**
	 * @return the {@link IWidgetGUI} the current {@link ILayer} belongs to.
	 * @throws RuntimeException if the current {@link ILayer} does not belong to a {@link IWidgetGUI}.
	 */
	IWidgetGUI<?> getRefParentGUI();
	
	//method declaration
	/**
	 * @return the root {@link IWidget} of the current {@link ILayer}.
	 * @throws RuntimeException if the current {@link ILayer} does not have a root {@link IWidget}.
	 */
	IWidget<?, ?> getRefRootWidget();
		
	//method declaration
	/**
	 * Paints the current {@link ILayer} using the given painter.
	 * 
	 * @param painter
	 */
	void paint(IPainter painter);
	
	//method declaration
	/**
	 * Removes the current {@link ILayer} from the {@link IWidgetGUI} it belongs to if
	 * the current {@link ILayer} belongs to a {@link IWidgetGUI}.
	 */
	void removeSelfFromGUI();
}
