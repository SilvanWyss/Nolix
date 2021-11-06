//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
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
	@Override
	default boolean isLinkedWithRealDatabase() {
		return (belongsToEntity() && getParentEntity().isLinkedWithRealDatabase());
	}
}
