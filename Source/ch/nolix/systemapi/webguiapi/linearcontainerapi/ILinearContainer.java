//package declaration
package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILinearContainer<
	LC extends ILinearContainer<LC, LCL>,
	LCL extends ILinearContainerStyle<LCL>
>
extends ch.nolix.systemapi.webguiapi.containerapi.IContainer<LC, LCL> {
	
	//method declaration
	LC addControl(IControl<?, ?>... controls);
	
	//method declaration
	LC addControls(IContainer<? extends IControl<?, ?>> controls);
	
	//method declaration
	void removeControl(IControl<?, ?> control);
}
