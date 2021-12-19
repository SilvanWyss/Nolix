//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordDeletionDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IOptionalValueHelper;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class OptionalValueHelper extends PropertyHelper implements IOptionalValueHelper {
	
	//method
	@Override
	public void assertHasValue(final IOptionalValue<?, ?> optionalValue) {
		if (optionalValue.isEmpty()) {
			throw new EmptyArgumentException(optionalValue);
		}
	}
	
	//method
	@Override
	public IRecordDeletionDTO createRecordDeletioDTOFor(final IOptionalValue<?, ?> optionalValue) {
		
		final var parentEntity = optionalValue.getParentEntity();
		
		return new RecordDeletionDTO(parentEntity.getId(), parentEntity.getSaveStamp());
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForValue(final IOptionalValue<?, ?> optionalValue, final Object value) {
		
		final var parentEntity = optionalValue.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalValue.getName(), value)
		);
	}
}
