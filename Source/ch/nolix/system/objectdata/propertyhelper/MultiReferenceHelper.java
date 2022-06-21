//package declaration
package ch.nolix.system.objectdata.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class MultiReferenceHelper extends PropertyHelper implements IMultiReferenceHelper {
	
	//method
	@Override
	public void assertCanAddGivenEntity(final IMultiReference<?, ?> multiReference, final IEntity<?> entity) {
		if (!canAddGivenEntity(multiReference, entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot add the given entity");
		}
	}
	
	//method
	@Override
	public void assertCanClear(final IMultiReference<?, ?> multiReference) {
		if (!canClear(multiReference)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot clear");
		}
	}
	
	@Override
	public boolean canAddGivenEntity(IMultiReference<?, ?> multiReference, IEntity<?> entity) {
		return
		canAddEntity(multiReference)
		&& entity != null
		&& entity.isOpen()
		&& multiReference.getReferencedTableName().equals(entity.getParentTableName());
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
			new ContentFieldDTO(multiReference.getName())
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
