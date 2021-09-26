//package
package ch.nolix.techapi.databaseapi.databaseobjectapi;

//own imports
import ch.nolix.common.requestapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable {
	
	//method declaration
	DatabaseObjectState getState();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
}
