//package declaration
package ch.nolix.element.gui;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.baseguiapi.IBaseGUI;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.elementapi.IConfigurableElement;

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
	WG setConfiguration(Configuration configuration);
}
