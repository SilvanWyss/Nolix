//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.core.skilluniversalapi.Recalculable;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.baseapi.CursorIcon;
import ch.nolix.systemapi.guiapi.baseapi.IOccupiableCanvasInputActionManager;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;
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
IConfigurableElement<L>,
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
	 * @return the root {@link Widget} of the current {@link ILayer}.
	 * @throws Exception if the current {@link ILayer} does not have a root {@link IWidget}.
	 */
	IWidget<?, ?> getRefRootWidget();
	
	//method declaration
	/**
	 * @return the {@link Widget}s of the current {@link ILayer}.
	 */
	IContainer<Widget<?, ?>> getRefWidgets();
	
	//method declaration
	/**
	 * @return the {@link Widget}s of the current {@link ILayer} that are supposed to be painted.
	 */
	IContainer<Widget<?, ?>> getRefWidgetsForPainting();
	
	//method declaration
	/**
	 * Paints the current {@link ILayer} using the given painter.
	 * 
	 * @param painter
	 */
	void paint(IPainter painter);
}
