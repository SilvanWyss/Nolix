//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.datadto.RecordUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;

//class
public final class OptionalReferenceHelper extends PropertyHelper implements IOptionalReferenceHelper {
	
	//method
	@Override
	public void assertCanClear(final IOptionalReference<?, ?> optionalReference) {
		if (!canClear(optionalReference)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalReference, "cannot clear");
		}
	}
	
	//method
	@Override
	public void assertCanSetGivenEntity(final IOptionalReference<?, ?> optionalReference, final IEntity<?> entity) {
		if (!canSetGivenEntity(optionalReference, entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalReference, "does not reference an entity");
		}
	}
	
	//method
	@Override
	public void assertIsNotEmpty(final IOptionalReference<?, ?> optionalReference) {
		if (optionalReference.isEmpty()) {
			throw EmptyArgumentException.forArgument(optionalReference);
		}
	}
	
	//method
	@Override
	public boolean canClear(final IOptionalReference<?, ?> optionalReference) {
		return
		optionalReference != null
		&& optionalReference.belongsToEntity()
		&& optionalReference.getParentEntity().isOpen();
	}
	
	//method
	@Override
	public boolean canSetGivenEntity(final IOptionalReference<?, ?> optionalReference, final IEntity<?> entity) {
		return
		canSetEntity(optionalReference)
		&& entity != null
		&& entity.isOpen()
		&& optionalReference.getReferencedTableName().equals(entity.getParentTableName());
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForClear(final IOptionalReference<?, ?> optionalReference) {
		
		final var parentEntity = optionalReference.getParentEntity();
		
		return new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalReference.getName())
		);
	}
	
	//method
	@Override
	public IRecordUpdateDTO createRecordUpdateDTOForSetEntity(
		final IOptionalReference<?, ?> optionalReference,
		final IEntity<?> entity
	) {
		
		final var parentEntity = optionalReference.getParentEntity();
		
		return new RecordUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalReference.getName(), entity.getId())
		);
	}
	
	//method
	private boolean canSetEntity(final IOptionalReference<?, ?> optionalReference) {
		return
		optionalReference != null
		&& optionalReference.belongsToEntity()
		&& optionalReference.getParentEntity().isOpen();
	}
}
