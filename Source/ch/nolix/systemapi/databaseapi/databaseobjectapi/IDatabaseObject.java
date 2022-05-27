//package
package ch.nolix.systemapi.databaseapi.databaseobjectapi;

import ch.nolix.core.requestuniversalapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable {
	
	//method declaration
	DatabaseObjectState getState();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
}
