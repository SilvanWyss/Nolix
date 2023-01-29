//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;

//interface
public interface IRootControlOwner<RCO extends IRootControlOwner<RCO>> extends Clearable {
	
	//method declaration
	IContainer<IControl<?, ?>> getRefControls();
	
	//method declaration
	IControl<?, ?> getRefRootControl();
	
	//method declaration
	RCO setRootControl(IControl<?, ?> rootControl);
}
