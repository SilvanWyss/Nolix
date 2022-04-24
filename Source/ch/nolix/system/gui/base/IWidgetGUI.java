//package declaration
package ch.nolix.system.gui.base;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.skillapi.Clearable;
import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.ImageApplication;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.baseapi.IBaseGUI;

//interface
public interface IWidgetGUI<WG extends IWidgetGUI<WG>> extends Clearable,  IBaseGUI<WG>, IConfigurableElement<WG>{
	
	//method declaration
	WG addLayerOnTop(Layer layer);
	
	//method declaration
	WG addLayerOnTop(Widget<?, ?> rootWidget);
	
	//method declaration
	IContainer<ChainedNode> getPaintCommands();
	
	//method declaration
	<W extends Widget<?, ?>> W getRefWidgetById(String id);
	
	//method declaration
	WG setBackgroundColor(Color backgroundColor);
	
	//method declaration
	WG setBackgroundImage(MutableImage backgroundImage);
	
	//method declaration
	WG setBackgroundImage(MutableImage backgroundImage, ImageApplication imageApplication);
	
	//method declaration
	WG setConfiguration(Configuration configuration);
}
