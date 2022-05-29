//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.functionuniversalapi.I2ElementTaker;
import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementuniversalapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.baseapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;

//interface
public interface IWidgetGUI<WG extends IWidgetGUI<WG>> extends Clearable,  IBaseGUI<WG>, IConfigurableElement<WG>{
	
	//method declaration
	LinkedList<ChainedNode> getPaintCommands(I2ElementTaker<String, IImage<?>> imageRegistrator);
	
	//method declaration
	<W extends Widget<?, ?>> W getRefWidgetById(String id);
	
	//method declaration
	WG pushLayer(Layer layer);
	
	//method declaration
	WG pushLayer(Widget<?, ?> rootWidget);
	
	//method declaration
	WG setBackgroundColor(IColor backgroundColor);
	
	//method declaration
	WG setBackgroundImage(MutableImage backgroundImage);
	
	//method declaration
	WG setBackgroundImage(MutableImage backgroundImage, ImageApplication imageApplication);
	
	//method declaration
	WG setConfiguration(Configuration configuration);
}
