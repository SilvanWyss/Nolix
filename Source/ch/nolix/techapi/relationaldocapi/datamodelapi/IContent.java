//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.entityproperty.ContentType;
import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

//interface
public interface IContent extends AbstractnessRequestable {
	
	//method declaration
	IAbstractableField getStoredParentField();
	
	//method declaration
	ContentType getType();
}
