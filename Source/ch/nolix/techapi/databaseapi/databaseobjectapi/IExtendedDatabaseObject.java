//package
package ch.nolix.techapi.databaseapi.databaseobjectapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonNewArgumentException;

//interface
public interface IExtendedDatabaseObject extends IDatabaseObject {
	
	//method
	default void assertIsLinkedWithRealDatabase() {
		if (!isLinkedWithRealDatabase()) {
			throw new InvalidArgumentException(this, "is not linked with a real database");
		}
	}
	
	//method
	default void assertIsLoaded() {
		if (!isLoaded()) {
			throw new InvalidArgumentException(this, "is not loaded");
		}
	}
	
	//method
	default void assertIsNew() {
		if (!isNew()) {
			throw new NonNewArgumentException(this);
		}
	}
	
	//method
	default void assertIsNotDeleted() {
		if (isDeleted()) {
			throw new DeletedArgumentException(this);
		}
	}
	
	//method
	default void assertIsNotLinkedWithActualDatabase() {
		if (isLinkedWithRealDatabase()) {
			throw new InvalidArgumentException(this, "is already linked with an actual database");
		}
	}
	
	//method
	default void assertIsNotNew() {
		if (isNew()) {
			throw new NewArgumentException(this);
		}
	}
	
	//method
	default boolean isDeleted() {
		return (getState() == DatabaseObjectState.DELETED);
	}
		
	//method
	default boolean isEdited() {
		return (getState() == DatabaseObjectState.EDITED);
	}
	
	//method
	default boolean isLoaded() {
		return (getState() == DatabaseObjectState.LOADED);
	}
	
	//method
	default boolean isNew() {
		return (getState() == DatabaseObjectState.NEW);
	}
}
