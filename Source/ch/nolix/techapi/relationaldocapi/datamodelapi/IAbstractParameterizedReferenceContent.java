//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;

//interface
public interface IAbstractParameterizedReferenceContent<AO extends IAbstractableObject>
extends IParameterizedReferenceContent<AO> {
	
	//method declaration
	IAbstractParameterizedValueContent<AO> addConstraint(IConstraint<AO> constraint);
	
	//method declaration
	IAbstractReferenceContent getStoredParentAbstractReferenceContent();
	
	//method declaration
	void removeConstraint(IConstraint<AO> constraint);
	
	//method declaration
	void removeConstraints();
}
