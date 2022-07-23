//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.systemapi.webguiapi.controllookapi.IExtendedControlLook;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IContainer<
	C extends IContainer<C, ECL>,
	ECL extends IExtendedControlLook<ECL>
>
extends Clearable, IControl<C, ECL> {
		
	//method declaration
	ContainerRole getRole();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeRole();
	
	//method declaration
	C setRole(ContainerRole role);
}
