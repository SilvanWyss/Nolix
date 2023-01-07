//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class MultiValueHelper extends PropertyHelper implements IMultiValueHelper {
	
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
		&& multiValue.getRefParentEntity().isOpen();
	}
	
	//method
	@Override
	public <V> boolean canRemoveValue(final IMultiValue<?, V> multiValue, final V value) {
		return
		canRemoveValue(multiValue)
		&& value != null;
	}
	
	//method
	@Override
	public <V> IEntityUpdateDTO createEntityUpdateDTOForAddedValue(
		final IMultiValue<?, V> multiValue,
		final V addedValue
	) {
		
		final var parentEntity = multiValue.getRefParentEntity();
		
		return
		new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiValue.getName(), "")
		);
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForClear(final IMultiValue<?, ?> multiValue) {
		
		final var parentEntity = multiValue.getRefParentEntity();
		
		return
		new EntityUpdateDTO(
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
		&& multiValue.getRefParentEntity().isOpen();
	}
	
	//method
	private boolean canRemoveValue(final IMultiValue<?, ?> multiValue) {
		return
		multiValue != null
		&& multiValue.isOpen();
	}
}
