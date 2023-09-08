//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi.IFluentMutableMultiValueHolder;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;

//interface
public interface IConcreteParameterizedValueContent<V>
extends
EmptinessRequestable,
IFluentMutableMultiValueHolder<IConcreteParameterizedValueContent<V>, V>,
IParameterizedValueContent<V> {
	
	//method declaration
	IConcreteValueContent getStoredParentConcreteValueContent();
}
