//package
package ch.nolix.techapi.databasecommonapi.databaseobjectapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ExpiredArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonNewArgumentException;

//interface
public interface IDatabaseObject {
	
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
	default void assertIsNotExpired() {
		if (isExpired()) {
			throw new ExpiredArgumentException(this);
		}
	}
	
	//method declaration
	DatabaseObjectState getState();
		
	//method
	default boolean isEdited() {
		return (getState() == DatabaseObjectState.EDITED);
	}
	
	//method
	default boolean isExpired() {
		return (getState() == DatabaseObjectState.EXPIRED);
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
