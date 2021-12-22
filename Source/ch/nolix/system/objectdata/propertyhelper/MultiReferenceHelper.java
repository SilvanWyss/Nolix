//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawobjectdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawobjectdata.datadto.RecordUpdateDTO;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class MultiReferenceHelper extends PropertyHelper implements IMultiReferenceHelper {
	
	//method
	@Override
	public void assertCanAddGivenEntity(final IMultiReference<?, ?> multiReference, final IEntity<?> entity) {
		if (!canAddGivenEntity(multiReference, entity)) {
			throw new InvalidArgumentException(multiReference, "cannot add the given entity");
		}
	}
	
	//method
	@Override
	public void assertCanClear(final IMultiReference<?, ?> multiReference) {
		if (!canClear(multiReference)) {
			throw new InvalidArgumentException(multiReference, "cannot clear");
		}
	}
	
	@Override
	public boolean canAddGivenEntity(IMultiReference<?, ?> multiReference, IEntity<?> entity) {
		return
		canAddEntity(multiReference)
		&& entity != null
		&& entity.isOpen()
		&& multiReference.getReferencedTableName().equals(entity.getTableName());
	}
	
	//method
	@Override
	public boolean canClear(IMultiReference<?, ?> multiReference) {
		return
		multiReference != null
		&& multiReference.belongsToEntity()
		&& multiReference.getParentEntity().isOpen();
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordupdateDTOForAddEntity(
		final IMultiReference<?, ?> multiReference,
		final IEntity<?> entity
	) {
		
		final var parentEntity = multiReference.getParentEntity();
		
		return
		new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiReference.getName(), multiReference.asContainerWithElementsOfEvaluatedType())
		);
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForClear(final IMultiReference<?, ?> multiReference) {
		
		final var parentEntity = multiReference.getParentEntity();
		
		return new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiReference.getName())
		);
	}
	
	//method
	private boolean canAddEntity(final IMultiReference<?, ?> multiReference) {
		return
		multiReference != null
		&& multiReference.belongsToEntity()
		&& multiReference.getParentEntity().isOpen();
	}
}
