//package declaration
package ch.nolix.techapi.objectdataapi.extendedstructuraldataapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.objectdataapi.extendeddataapi.IExtendedEntity;
import ch.nolix.techapi.objectdataapi.structuraldataapi.IProperty;
import ch.nolix.techapi.objectdataapi.structuraldataapi.IStructuralEntity;

//interface
public interface IExtendedStructuralEntity<
    ESE extends IExtendedStructuralEntity<ESE, P>,
    P extends IProperty<P>
> extends IExtendedEntity<ESE>, IStructuralEntity<ESE, P> {
    
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
    
    //method declaration
    boolean isReferenced();
}
