//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqlrawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

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
		&& optionalReference.getRefParentEntity().isOpen();
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
	public IEntityUpdateDTO createRecordUpdateDTOForClear(final IOptionalReference<?, ?> optionalReference) {
		
		final var parentEntity = optionalReference.getRefParentEntity();
		
		return new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalReference.getName())
		);
	}
	
	//method
	@Override
	public IEntityUpdateDTO createRecordUpdateDTOForSetEntity(
		final IOptionalReference<?, ?> optionalReference,
		final IEntity<?> entity
	) {
		
		final var parentEntity = optionalReference.getRefParentEntity();
		
		return new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalReference.getName(), entity.getId())
		);
	}
	
	//method
	@Override
	public <IMPL> IProperty<IMPL> getRefBackReferencingPropertyOrNull(
		final IOptionalReference<IMPL, ?> optionalReference
	) {
		return
		optionalReference
		.getRefEntity()
		.technicalGetRefProperties()
		.getRefFirstOrNull(p -> p.referencesBackProperty(optionalReference));
	}
	
	//method
	private boolean canSetEntity(final IOptionalReference<?, ?> optionalReference) {
		return
		optionalReference != null
		&& optionalReference.belongsToEntity()
		&& optionalReference.getRefParentEntity().isOpen();
	}
}
