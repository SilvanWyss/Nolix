//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//interface
public interface IOptionalValueHelper extends IPropertyHelper {
	
	//method declaration
	boolean canSetGivenValue(IOptionalValue<?, ?> optionalValue, Object value);
	
	//method declaration
	IEntityUpdateDTO createEntityUpdateDTOForSetValue(IOptionalValue<?, ?> optionalValue, Object value);
}
