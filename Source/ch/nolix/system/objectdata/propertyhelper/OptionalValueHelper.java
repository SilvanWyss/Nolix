//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IOptionalValueHelper;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class OptionalValueHelper extends PropertyHelper implements IOptionalValueHelper {
	
	//method
	@Override
	public void assertCanSetGivenValue(final IOptionalValue<?, ?> optionalValue, final Object value) {
		if (!canSetGivenValue(optionalValue, value)) {
			throw new InvalidArgumentException(optionalValue, "cannot set the given value");
		}
	}
	
	//method
	@Override
	public void assertHasValue(final IOptionalValue<?, ?> optionalValue) {
		if (optionalValue.isEmpty()) {
			throw new EmptyArgumentException(optionalValue);
		}
	}
	
	@Override
	public boolean canSetGivenValue(final IOptionalValue<?, ?> optionalValue, final Object value) {
		return
		canSetValue(optionalValue)
		&& value != null;
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForClear(final IOptionalValue<?, ?> optionalValue) {
		
		final var parentEntity = optionalValue.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalValue.getName())
		);
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForSetValue(
		final IOptionalValue<?, ?> optionalValue,
		final Object value
	) {
		
		final var parentEntity = optionalValue.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalValue.getName(), value)
		);
	}
	
	//method
	private boolean canSetValue(final IOptionalValue<?, ?> optionalValue) {
		return
		optionalValue != null
		&& optionalValue.belongsToEntity()
		&& optionalValue.getParentEntity().isOpen();
	}
}
