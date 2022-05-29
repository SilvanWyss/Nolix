//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.functionuniversalapi.I2ElementTaker;
import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.elementapi.configurationapi.IConfiguration;
import ch.nolix.systemapi.guiapi.baseapi.IBaseGUI;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.inputdeviceapi.IKeyBoard;

//interface
public interface IWidgetGUI<WG extends IWidgetGUI<WG>> extends Clearable,  IBaseGUI<WG>, IConfigurableElement<WG>{
	
	//method declaration
	LinkedList<ChainedNode> getPaintCommands(I2ElementTaker<String, IImage> imageRegistrator);
	
	//method declaration
	IKeyBoard getRefKeyBoard();
	
	//method declaration
	<W extends Widget<?, ?>> W getRefWidgetById(String id);
	
	//method declaration
	IContainer<Widget<?, ?>> getRefWidgets();
	
	//method declaration
	WG pushLayer(ILayer<?> layer);
	
	//method declaration
	WG pushLayer(Widget<?, ?> rootWidget);
	
	//method declaration
	void removeLayer(ILayer<?> layer);
	
	//method declaration
	WG setBackgroundColor(IColor backgroundColor);
	
	//method declaration
	WG setBackgroundImage(IImage backgroundImage);
	
	//method declaration
	WG setBackgroundImage(IImage backgroundImage, ImageApplication imageApplication);
	
	//method declaration
	WG setConfiguration(IConfiguration configuration);
	
	//method declaration
	boolean viewAreaIsUnderCursor();
}
