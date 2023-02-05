//package declaration
package ch.nolix.systemapi.elementapi.multistateelementapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;

//interface
public interface IMultiStateElement<
	MSE extends IMultiStateElement<MSE, S>,
	S extends Enum<S>
>
extends IRespondingMutableElement<MSE> {
	
	//method declaration
	S getBaseState();
}
