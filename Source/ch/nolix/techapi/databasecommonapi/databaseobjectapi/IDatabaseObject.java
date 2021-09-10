//package
package ch.nolix.techapi.databasecommonapi.databaseobjectapi;

//own imports
import ch.nolix.common.requestapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable {
	
	//method declaration
	DatabaseObjectState getState();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
}
