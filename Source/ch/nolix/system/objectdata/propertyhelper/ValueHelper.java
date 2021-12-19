//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.techapi.objectdataapi.dataapi.IValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IValueHelper;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class ValueHelper extends PropertyHelper implements IValueHelper {
	
	//method
	@Override
	public void assertHasValue(final IValue<?, ?> value) {
		if (!value.hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
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
}
