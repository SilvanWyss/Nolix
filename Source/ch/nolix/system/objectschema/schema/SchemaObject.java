//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//class
abstract class SchemaObject implements IDatabaseObject {
	
	//attribute
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//method
	@Override
	public final DatabaseObjectState getState() {
		return state;
	}
	
	//method
	@Override
	public final boolean isClosed() {
		return (getState() == DatabaseObjectState.CLOSED);
	}
	
	//method
	@Override
	public final boolean isDeleted() {
		return (getState() == DatabaseObjectState.DELETED);
	}
	
	//method declaration
	protected abstract void noteClose();
	
	//method
	final void internalClose() {
		
		state = DatabaseObjectState.CLOSED;
		
		noteClose();
	}
	
	//method
	final void internalSetDeleted() {
		switch (getState()) {
			case NEW:
				throw new NewArgumentException(this);
			case LOADED:
			case EDITED:
				state = DatabaseObjectState.DELETED;
				break;
			case DELETED:
				throw DeletedArgumentException.forArgument(this);
			case CLOSED:
				throw ClosedArgumentException.forArgument(this);
		}
	}
	
	//method
	final void internalSetEdited() {
		switch (getState()) {
			case NEW:
				break;
			case LOADED:
				state = DatabaseObjectState.EDITED;
				break;
			case EDITED:
				break;
			case DELETED:
				throw DeletedArgumentException.forArgument(this);
			case CLOSED:
				throw ClosedArgumentException.forArgument(this);
		}
	}
	
	//method
	final void internalSetLoaded() {
		switch (getState()) {
			case NEW:
				state = DatabaseObjectState.LOADED;
				break;
			case LOADED:
				throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already loaded");
			case EDITED:
				throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already edited");
			case DELETED:
				throw DeletedArgumentException.forArgument(this);
			case CLOSED:
				throw ClosedArgumentException.forArgument(this);
		}
	}
}
