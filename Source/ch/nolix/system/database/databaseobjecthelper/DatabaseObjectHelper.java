//package declaration
package ch.nolix.system.database.databaseobjecthelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;

//class
public class DatabaseObjectHelper implements IDatabaseObjectHelper {
	
	//method
	@Override
	public final void assertIsLinkedWithRealDatabase(final IDatabaseObject databaseObject) {
		if (!databaseObject.isLinkedWithRealDatabase()) {
			throw new InvalidArgumentException(databaseObject, "is not linked with a real database");
		}
	}
	
	//method
	@Override
	public final void assertIsLoaded(final IDatabaseObject databaseObject) {
		if (!isLoaded(databaseObject)) {
			throw new InvalidArgumentException(databaseObject, "is not loaded");
		}
	}
	
	//method
	@Override
	public final void assertIsNew(final IDatabaseObject databaseObject) {
		if (!isNew(databaseObject)) {
			throw new InvalidArgumentException(databaseObject, "is not new");
		}
	}
	
	//method
	@Override
	public final void assertIsNotDeleted(final IDatabaseObject databaseObject) {
		if (databaseObject.isDeleted()) {
			throw DeletedArgumentException.forArgument(databaseObject);
		}
	}
	
	//method
	@Override
	public final void assertIsNotLinkedWithRealDatabase(final IDatabaseObject databaseObject) {
		if (databaseObject.isLinkedWithRealDatabase()) {
			throw new InvalidArgumentException(databaseObject, "is linked with a real database");
		}
	}
	
	//method
	@Override
	public final void assertIsNotNew(final IDatabaseObject databaseObject) {
		if (isNew(databaseObject)) {
			throw new InvalidArgumentException(databaseObject, "is new");
		}
	}
	
	//method
	@Override
	public boolean isDeleted(final IDatabaseObject databaseObject) {
		return (databaseObject.getState() == DatabaseObjectState.DELETED);
	}
	
	//method
	@Override
	public boolean isEdited(final IDatabaseObject databaseObject) {
		return (databaseObject.getState() == DatabaseObjectState.EDITED);
	}
	
	//method
	@Override
	public boolean isLoaded(final IDatabaseObject databaseObject) {
		return (databaseObject.getState() == DatabaseObjectState.LOADED);
	}
	
	//method
	@Override
	public boolean isNew(final IDatabaseObject databaseObject) {
		return (databaseObject.getState() == DatabaseObjectState.NEW);
	}
}
