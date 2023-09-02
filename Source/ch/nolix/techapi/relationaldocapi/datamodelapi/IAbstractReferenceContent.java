//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//interface
public interface IAbstractReferenceContent extends IReferenceContent {
	
	//method declaration
	IAbstractParameterizedReferenceContent<?> getStoredAbstractParameterizedReferenceContent();
	
	//method declaration
	IAbstractReferenceContent setReferencedType(IAbstractableObject referenceType);
}
