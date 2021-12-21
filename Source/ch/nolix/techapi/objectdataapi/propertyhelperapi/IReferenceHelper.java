//package declaration
package ch.nolix.techapi.objectdataapi.propertyhelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IReference;

//interface
public interface IReferenceHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity);
	
	//method declaration
	void assertReferencesEntity(IReference<?, ?> referenc);
	
	//method
	boolean canSetGivenEntity(final IReference<?, ?> reference, final IEntity<?> entity);
}
