//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class OptionalValueHelper extends PropertyHelper implements IOptionalValueHelper {
	
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
	public IEntityUpdateDTO createEntityUpdateDTOForSetValue(
		final IOptionalValue<?, ?> optionalValue,
		final Object value
	) {
		
		final var parentEntity = optionalValue.getRefParentEntity();
		
		return
		new EntityUpdateDTO(
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
