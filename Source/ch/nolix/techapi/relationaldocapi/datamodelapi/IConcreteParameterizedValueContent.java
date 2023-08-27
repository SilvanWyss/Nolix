//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi.IFluentMutableMultiValueHolder;

//interface
public interface IConcreteParameterizedValueContent<V>
extends
IFluentMutableMultiValueHolder<IConcreteParameterizedValueContent<V>, V>,
IParameterizedValueContent<V> {
	
	//method declaration
	IConcreteValueContent getStoredParentConcreteValueContent();
}
