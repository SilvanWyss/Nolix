//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasedto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;

//class
public final class ValueHelper extends PropertyHelper implements IValueHelper {
	
	//method
	@Override
	public void assertCanSetGivenValue(final IValue<?, ?> value, final Object valueToSet) {
		if (!canSetGivenValue(value, valueToSet)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
		}
	}
	
	//method
	@Override
	public boolean canSetGivenValue(final IValue<?, ?> value, final Object valueToSet) {
		return
		canSetValue(value)
		&& valueToSet != null;
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForSetValue(final IValue<?, ?> value, final Object setValue) {
		
		final var parentEntity = value.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(value.getName(), setValue.toString())
		);
	}
	
	//method
	private boolean canSetValue(final IValue<?, ?> value) {
		return
		value != null
		&& value.isOpen();
	}
}
