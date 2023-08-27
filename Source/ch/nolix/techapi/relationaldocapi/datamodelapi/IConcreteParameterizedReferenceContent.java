//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//interface
public interface IConcreteParameterizedReferenceContent<AO extends IAbstractableObject>
extends IParameterizedReferenceContent<AO> {
	
	//method declaration
	IConcreteParameterizedReferenceContent<AO> addObject(AO object);
	
	//method declaration
	IConcreteReferenceContent getStoredParentConcreteReferenceContent();
	
	//method declaration
	void removeObject(AO object);
	
	//method declaration
	void removeObjects();
}
