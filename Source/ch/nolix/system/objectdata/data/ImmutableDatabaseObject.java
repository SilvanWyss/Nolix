//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.programcontrol.groupcloseable.CloseController;
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

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
