//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;

//interface
public interface IParameterizedReferenceContent<AO extends IAbstractableObject> {
	
	//method declaration
	IContainer<IConstraint<AO>> getStoredConstraints();
}
