//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//interface
public interface IConcreteReferenceContent extends IReferenceContent {
	
	//method declaration
	IConcreteReferenceContent addObject(IAbstractableObject object);
	
	//method declaration
	IConcreteReferenceContent getStoredParentConcreteReferenceContent();
	
	//method declaration
	void removeObject(IAbstractableObject object);
	
	//method declaration
	void removeObjects();
	
	//method declaration
	IConcreteReferenceContent setReferencedType(IAbstractableObject referencedType);
}
