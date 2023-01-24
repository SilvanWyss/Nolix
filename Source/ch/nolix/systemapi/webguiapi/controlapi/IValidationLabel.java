//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IValidationLabel<
	VL extends IValidationLabel<VL, VLS>,
	VLS extends IValidationLabelStyle<VLS>
>
extends Clearable, IControl<VL, VLS> {
	
	//method declaration
	Throwable getError();
	
	//method declaration
	void showError(Throwable error);
}
