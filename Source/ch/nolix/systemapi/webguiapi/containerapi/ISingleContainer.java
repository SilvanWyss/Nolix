//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {
	
	//method declaration
	IControl<?, ?> getOriControl();
	
	//method declaration
	ISingleContainer setControl(IControl<?, ?> control);
}
