//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//interface
public interface IReferenceContent extends IContent {
	
	//method declaration
	Class<IAbstractableObject> getReferencedType();
}
