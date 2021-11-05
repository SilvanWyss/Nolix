//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databaseapi.extendeddatabaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;

//interface
public interface IExtendedEntity<
    EE extends IExtendedEntity<EE, P>,
    P extends IProperty<P>
> extends IEntity<EE, P>, IExtendedDatabaseObject {
    
	//method
	default void assertBelongsToTable() {
		if (!belongsToTable()) {
			throw new ArgumentDoesNotBelongToParentException(this, LowerCaseCatalogue.TABLE);
		}
	}
	
	//method
	default void assertIsNotBackReferenced() {
		if (isBackReferenced() ) {
			 throw new InvalidArgumentException(this, "is back referenced");
		}
	}
	
    //method
    default void assertIsNotReferenced() {
        if (isReferenced()) {
            throw new InvalidArgumentException(this, "is referenced");
        }
    }
    
    //method
    @Override
    default boolean isLinkedWithRealDatabase() {
    	return (belongsToTable() && getParentTable().isLinkedWithRealDatabase());
    }
}
