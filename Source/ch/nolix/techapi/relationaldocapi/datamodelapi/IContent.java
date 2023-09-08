//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;
import ch.nolix.coreapi.datamodelapi.entityrequestapi.ContentTypeRequestable;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;

//interface
public interface IContent extends AbstractnessRequestable, ContentTypeRequestable, EmptinessRequestable {
	
	//method declaration
	IAbstractableField getStoredParentField();
}
