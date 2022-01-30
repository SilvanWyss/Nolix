//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//class
abstract class ImmutableDatabaseObject implements GroupCloseable, IDatabaseObject {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		return DatabaseObjectState.LOADED;
	}
	
	//method
	@Override
	public final boolean isDeleted() {
		return false;
	}
	
	//method
	@Override
	public final boolean isLinkedWithRealDatabase() {
		return true;
	}
	
	//method
	@Override
	public final void noteClose() {}
}
