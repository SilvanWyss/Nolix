//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;

//interface
public interface IAbstractParameterizedValueContent<V> extends IParameterizedValueContent<V> {
	
	//method declaration
	IAbstractParameterizedValueContent<V> addConstraint(IConstraint<V> constraint);
	
	//method declaration
	IAbstractValueContent getStoredParentAbstractValueContent();
	
	//method declaration
	void removeConstraint(IConstraint<V> constraint);
	
	//method declaration
	void removeConstraints();
}
