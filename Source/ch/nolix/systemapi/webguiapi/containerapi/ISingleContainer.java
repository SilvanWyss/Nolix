//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {
	
	//method declaration
	IControl<?, ?> getStoredControl();
	
	//method declaration
	ISingleContainer setControl(IControl<?, ?> control);
}
