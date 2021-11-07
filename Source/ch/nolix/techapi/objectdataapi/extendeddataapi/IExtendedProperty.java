//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;

//interface
public interface IExtendedProperty<EP extends IExtendedProperty<EP>> extends IProperty<EP> {
	
	//method
	default void assertBelongsToEntity() {
		if (!belongsToEntity()) {
			throw new ArgumentDoesNotBelongToParentException(this, IEntity.class);
		}
	}
	
	//method
	default void assertDoesNotBelongToEntity() {
		if (belongsToEntity()) {
			throw new ArgumentBelongsToParentException(this, getParentEntity());
		}
	}
	
	//method
	@Override
	default boolean isLinkedWithRealDatabase() {
		return (belongsToEntity() && getParentEntity().isLinkedWithRealDatabase());
	}
}
