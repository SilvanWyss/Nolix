//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.techapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class MultiValueHelper extends PropertyHelper implements IMultiValueHelper {
	
	//method
	@Override
	public <V> IRecordUpdateDTO createRecordUpdateDTOForAddedValue(
		final IMultiValue<?, V> multiValue,
		final V addedValue
	) {
		
		final var parentEntity = multiValue.getParentEntity();
		
		return new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiValue.getName(), multiValue)
		);
	}
}
