//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IRootControlOwner<RCO extends IRootControlOwner<RCO>> extends Clearable {
	
	//method declaration
	IContainer<IControl<?, ?>> getStoredControls();
	
	//method declaration
	IControl<?, ?> getStoredRootControl();
	
	//method declaration
	RCO setRootControl(IControl<?, ?> rootControl);
}
