//package
package ch.nolix.systemapi.databaseapi.databaseobjectapi;

//own imports
import ch.nolix.coreapi.functionapi.requestuniversalapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable {
	
	//method declaration
	DatabaseObjectState getState();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
}
