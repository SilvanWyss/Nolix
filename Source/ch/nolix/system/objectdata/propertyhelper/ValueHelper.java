//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class ValueHelper extends PropertyHelper implements IValueHelper {
	
	@Override
	public void assertCanSetGivenValue(final IValue<?, ?> value, final Object valueToSet) {
		if (!canSetGivenValue(value, valueToSet)) {
			throw new InvalidArgumentException(value, "cannot set the given value");
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
			new ContentFieldDTO(value.getName(), setValue)
		);
	}

	//method
	private boolean canSetValue(final IValue<?, ?> value) {
		return
		value != null
		&& value.belongsToEntity()
		&& value.getParentEntity().isOpen();
	}
}
