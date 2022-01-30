//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//class
abstract class DatabaseObject implements GroupCloseable, IDatabaseObject {
	
	//attributes
	private final CloseController closeController = new CloseController(this);
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		return state;
	}
	
	//method
	@Override
	public boolean isDeleted() {
		return (getState() == DatabaseObjectState.DELETED);
	}
	
	//method
	@Override
	public final void noteClose() {
		state = DatabaseObjectState.CLOSED;
		noteCloseDatabaseObject();
	}
	
	//method declaration
	protected abstract void noteCloseDatabaseObject();
	
	//method
	final void setDeleted() {
		switch (getState()) {
			case NEW:
				throw new NewArgumentException(this);
			case LOADED:
			case EDITED:
				state = DatabaseObjectState.DELETED;
				break;
			case DELETED:
				throw new DeletedArgumentException(this);
			case CLOSED:
				throw new ClosedArgumentException(this);
		}
	}
	
	//method
	final void setEdited() {
		switch (getState()) {
			case NEW:
				break;
			case LOADED:
				state = DatabaseObjectState.EDITED;
				break;
			case EDITED:
				break;
			case DELETED:
				throw new DeletedArgumentException(this);
			case CLOSED:
				throw new ClosedArgumentException(this);
		}
	}
	
	//method
	final void setLoaded() {
		switch (getState()) {
			case NEW:
				state = DatabaseObjectState.LOADED;
				break;
			case LOADED:
				throw new InvalidArgumentException(this, "is already loaded");
			case EDITED:
				throw new InvalidArgumentException(this, "is already edited");
			case DELETED:
				throw new DeletedArgumentException(this);
			case CLOSED:
				throw new ClosedArgumentException(this);
		}
	}
}
