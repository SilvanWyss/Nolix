//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;
import ch.nolix.coreapi.datamodelapi.entityrequestapi.ContentTypeRequestable;

//interface
public interface IContent extends AbstractnessRequestable, ContentTypeRequestable {
	
	//method declaration
	IAbstractableField getStoredParentField();
}
