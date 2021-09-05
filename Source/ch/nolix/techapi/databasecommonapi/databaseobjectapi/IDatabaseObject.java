//package
package ch.nolix.techapi.databasecommonapi.databaseobjectapi;

import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;

//interface
public interface IDatabaseObject extends GroupCloseable {
	
	//method declaration
	DatabaseObjectState getState();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
}
