//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;

//interface
public interface IAbstractReferenceContent extends IReferenceContent {
	
	//method declaration
	IAbstractReferenceContent addConstraint(IConstraint<IAbstractableObject> constraint);
		
	//method declaration
	IAbstractableObject getStoredReferencedType();
	
	//method declaration
	void removeConstraint(IConstraint<IAbstractableObject> constraint);
	
	//method declaration
	void removeConstraints();
	
	//method declaration
	IAbstractReferenceContent setReferencedType(IAbstractableObject referencedType);
}
