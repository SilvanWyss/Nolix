//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.programcontrol.groupcloseable.CloseController;
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;

//class
abstract class DatabaseObject implements GroupCloseable, IExtendedDatabaseObject {
	
	//attributes
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	private final CloseController closeController = new CloseController(this);
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		return state;
	}
	
	//method
	@Override
	public final void noteClose() {
		state = DatabaseObjectState.CLOSED;
		noteCloseDatabaseObject();
	}
	
	//method declaration
	protected abstract void noteCloseDatabaseObject();
}
