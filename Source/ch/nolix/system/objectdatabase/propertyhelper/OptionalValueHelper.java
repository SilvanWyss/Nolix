//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalValueHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class OptionalValueHelper extends PropertyHelper implements IOptionalValueHelper {
	
	//method
	@Override
	public void assertCanSetGivenValue(final IOptionalValue<?, ?> optionalValue, final Object value) {
		if (!canSetGivenValue(optionalValue, value)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot set the given value");
		}
	}
	
	//method
	@Override
	public void assertHasValue(final IOptionalValue<?, ?> optionalValue) {
		if (optionalValue.isEmpty()) {
			throw EmptyArgumentException.forArgument(optionalValue);
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
			new ContentFieldDTO(optionalValue.getName(), value.toString())
		);
	}
	
	//method
	private boolean canSetValue(final IOptionalValue<?, ?> optionalValue) {
		return
		optionalValue != null
		&& optionalValue.isOpen();
	}
}
