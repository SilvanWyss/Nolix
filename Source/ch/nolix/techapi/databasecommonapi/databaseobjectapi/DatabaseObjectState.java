//package declaration
package ch.nolix.techapi.databasecommonapi.databaseobjectapi;

//enum
public enum DatabaseObjectState {
	
	//not yet persisted in database
	NEW,
	
	//loaded from database
	LOADED,
	
	//loaded from database and edited
	EDITED,
	
	//loaded from database and deleted
	DELETED,
	
	//not valid anymore
	CLOSED
}
