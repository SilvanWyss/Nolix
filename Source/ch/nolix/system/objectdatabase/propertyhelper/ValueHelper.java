//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class ValueHelper extends PropertyHelper implements IValueHelper {
	
	//method
	@Override
	public boolean canSetGivenValue(final IValue<?> value, final Object valueToSet) {
		return
		canSetValue(value)
		&& valueToSet != null;
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForSetValue(final IValue<?> value, final Object setValue) {
		
		final var parentEntity = value.getRefParentEntity();
		
		return
		new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(value.getName(), setValue.toString())
		);
	}
	
	//method
	private boolean canSetValue(final IValue<?> value) {
		return
		value != null
		&& value.isOpen();
	}
}
