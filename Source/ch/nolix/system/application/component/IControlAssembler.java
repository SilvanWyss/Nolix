//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IControlAssembler<C extends Controller<AC>, AC> {
	
	//method declaration
	IControl<?, ?> createControl(C controller);
}
