//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class OptionalReferenceHelper extends PropertyHelper implements IOptionalReferenceHelper {
		
	//method
	@Override
	public boolean canClear(final IOptionalReference<?> optionalReference) {
		return
		optionalReference != null
		&& optionalReference.belongsToEntity()
		&& optionalReference.getOriParentEntity().isOpen();
	}
	
	//method
	@Override
	public boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
		return
		canSetEntity(optionalReference)
		&& entity != null
		&& entity.isOpen()
		&& optionalReference.getOrierencedTableName().equals(entity.getParentTableName());
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForClear(final IOptionalReference<?> optionalReference) {
		
		final var parentEntity = optionalReference.getOriParentEntity();
		
		return new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalReference.getName())
		);
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForSetEntity(
		final IOptionalReference<?> optionalReference,
		final IEntity entity
	) {
		
		final var parentEntity = optionalReference.getOriParentEntity();
		
		return new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(optionalReference.getName(), entity.getId())
		);
	}
	
	//method
	@Override
	public  IProperty getOriBackReferencingPropertyOrNull(
		final IOptionalReference<?> optionalReference
	) {
		return
		optionalReference
		.getOrierencedEntity()
		.technicalGetRefProperties()
		.getOriFirstOrNull(p -> p.referencesBackProperty(optionalReference));
	}
	
	//method
	private boolean canSetEntity(final IOptionalReference<?> optionalReference) {
		return
		optionalReference != null
		&& optionalReference.belongsToEntity()
		&& optionalReference.getOriParentEntity().isOpen();
	}
}
