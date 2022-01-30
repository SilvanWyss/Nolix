//package declaration
package ch.nolix.system.objectdata.propertyhelper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.data.MultiValue;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class MultiValueHelper extends PropertyHelper implements IMultiValueHelper {
	
	//method
	@Override
	public void assertCanAddGivenValue(final IMultiValue<?, ?> multiValue, final Object value) {
		if (!canAddGivenValue(multiValue, value)) {
			throw new InvalidArgumentException(multiValue, "cannot add the given value");
		}
	}
	
	//method
	@Override
	public void assertCanClear(final IMultiValue<?, ?> multiValue) {
		if (!canClear(multiValue)) {
			throw new InvalidArgumentException(multiValue, "cannot clear");
		}
	}
	
	//method
	@Override
	public boolean canAddGivenValue(final IMultiValue<?, ?> multiValue, final Object value) {
		return
		assertCanAddValue(multiValue)
		&& value != null;
	}
	
	//method
	@Override
	public boolean canClear(final IMultiValue<?, ?> multiValue) {
		return
		multiValue != null
		&& multiValue.belongsToEntity()
		&& multiValue.getParentEntity().isOpen();
	}
	
	//method
	@Override
	public <V> IRecordUpdateDTO createRecordUpdateDTOForAddedValue(
		final IMultiValue<?, V> multiValue,
		final V addedValue
	) {
		
		final var parentEntity = multiValue.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiValue.getName(), multiValue.asContainerWithElementsOfEvaluatedType())
		);
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForClear(final MultiValue<?> multiValue) {
		
		final var parentEntity = multiValue.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiValue.getName())
		);
	}
	
	//method
	private boolean assertCanAddValue(final IMultiValue<?, ?> multiValue) {
		return
		multiValue != null
		&& multiValue.belongsToEntity()
		&& multiValue.getParentEntity().isOpen();
	}
}
