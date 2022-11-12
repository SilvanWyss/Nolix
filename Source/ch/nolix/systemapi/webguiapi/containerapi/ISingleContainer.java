//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ISingleContainer<
	SC extends ISingleContainer<SC, SCS>,
	SCS extends ISingleContainerStyle<SCS>
>
extends IContainer<SC, SCS> {
	
	//method declaration
	IControl<?, ?> getRefControl();
	
	//method declaration
	SC setControl(IControl<?, ?> control);
}
