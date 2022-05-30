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
public interface ILayer<L extends ILayer<L>>
extends
Clearable,
IConfigurableElement<L>,
IOccupiableCanvasInputActionManager<L>,
IResizableInputTaker,
Recalculable {
	
	//method declaration
	boolean allowesConfiguration();
	
	//method declaration
	CursorIcon getCursorIcon();
	
	//method declaration
	int getCursorXPosition();
	
	//method declaration
	int getCursorYPosition();
	
	//method declaration
	IWidget<?, ?> getRefRootWidget();
	
	//method declaration
	IContainer<Widget<?, ?>> getRefWidgets();
	
	//method declaration
	IContainer<Widget<?, ?>> getRefWidgetsForPainting();
	
	//method declaration
	void paint(IPainter painter);
	
	//method declaration
	void setParentGUI(IWidgetGUI<?> parentGUI);
}
