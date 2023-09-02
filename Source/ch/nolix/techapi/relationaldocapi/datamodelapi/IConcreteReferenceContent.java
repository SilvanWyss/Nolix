//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//interface
public interface IConcreteReferenceContent extends IReferenceContent {
	
	//method declaration
	IConcreteParameterizedReferenceContent<IAbstractableObject> getStoredConcreteParameterizedReferenceContent();
	
	//method declaration
	IConcreteReferenceContent setReferencedType(IAbstractableObject referencedType);
}
