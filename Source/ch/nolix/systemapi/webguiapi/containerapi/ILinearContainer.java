//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ILinearContainer<
	LC extends ILinearContainer<LC, LCL>,
	LCL extends ILinearContainerLook<LCL>
>
extends Clearable, IControl<LC, LCL> {
	
	//method declaration
	LC addControl(IControl<?, ?>... controls);
	
	//method declaration
	LC addControls(IContainer<? extends IControl<?, ?>> controls);
	
	//method declaration
	void removeControl(IControl<?, ?> control);
}
