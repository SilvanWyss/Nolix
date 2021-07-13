//package
package ch.nolix.techapi.databasecommonapi.databaseobjectapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonNewArgumentException;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;

//interface
public interface IDatabaseObject extends ICloseableElement {
	
	//method
	default void assertIsLinkedWithActualDatabase() {
		if (!isLinkedWithActualDatabase()) {
			throw new InvalidArgumentException(this, "is not linked with an actual database");
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
	default void assertIsNotLinkedWithActualDatabase() {
		if (isLinkedWithActualDatabase()) {
			throw new InvalidArgumentException(this, "is already linked with an actual database");
		}
	}
	
	//method declaration
	DatabaseObjectState getState();
		
	//method
	default boolean isEdited() {
		return (getState() == DatabaseObjectState.EDITED);
	}
	
	//method declaration
	boolean isLinkedWithActualDatabase();
	
	//method
	default boolean isLoaded() {
		return (getState() == DatabaseObjectState.LOADED);
	}
	
	//method
	default boolean isNew() {
		return (getState() == DatabaseObjectState.NEW);
	}
}
