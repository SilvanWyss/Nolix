//package declaration
package ch.nolix.techapi.objectdataapi.extendeddataapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IProperty;

//interface
public interface IExtendedEntity<
    EE extends IExtendedEntity<EE, P>,
    P extends IProperty<P>
> extends IEntity<EE, P>, IExtendedDatabaseObject {
    
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
    
    //method declaration
    boolean isBackReferenced();
    
    //method
    @Override
    default boolean isLinkedWithRealDatabase() {
    	return (belongsToTable() && getParentTable().isLinkedWithRealDatabase());
    }
    
    //method declaration
    boolean isReferenced();
}
