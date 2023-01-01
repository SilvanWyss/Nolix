//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//interface
public interface IMultiValueHelper extends IPropertyHelper {
	
	//method declaration
	void assertCanAddGivenValue(IMultiValue<?, ?> multiValue, Object value);
	
	//method declaration
	void assertCanClear(IMultiValue<?, ?> multiValue);
	
	//method declaration
	boolean canAddGivenValue(IMultiValue<?, ?> multiValue, Object value);
	
	//method declaration
	boolean canClear(IMultiValue<?, ?> multiValue);
	
	//method declaration
	<V> IEntityUpdateDTO createEntityUpdateDTOForAddedValue(IMultiValue<?, V> multiValue, V addedValue);
	
	//method declaration
	IEntityUpdateDTO createEntityUpdateDTOForClear(IMultiValue<?, ?> multiValue);
}
