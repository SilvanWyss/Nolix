//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;

//interface
public interface IParameterizedValueContent<V> {
	
	//method declaration
	IContainer<IConstraint<V>> getStoredConstraints();
}
