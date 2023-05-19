//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDTO;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDTO;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;

//class
public final class MultiReferenceHelper extends PropertyHelper implements IMultiReferenceHelper {
	
	@Override
	public boolean canAddGivenEntity(IMultiReference<?> multiReference, IEntity entity) {
		return
		canAddEntity(multiReference)
		&& entity != null
		&& entity.isOpen()
		&& multiReference.getOrierencedTableName().equals(entity.getParentTableName());
	}
	
	//method
	@Override
	public boolean canClear(IMultiReference<?> multiReference) {
		return
		multiReference != null
		&& multiReference.belongsToEntity()
		&& multiReference.getOriParentEntity().isOpen();
	}
	
	//method
	@Override
	public <E extends IEntity> boolean canRemoveEntity(
		final IMultiReference<E> multiReference,
		final E entity
	) {
		return
		canRemoveEntity(multiReference)
		&& entity.isOpen();
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForAddEntity(
		final IMultiReference<?> multiReference,
		final IEntity entity
	) {
		
		final var parentEntity = multiReference.getOriParentEntity();
		
		return
		new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiReference.getName())
		);
	}
	
	//method
	@Override
	public IEntityUpdateDTO createEntityUpdateDTOForClear(final IMultiReference<?> multiReference) {
		
		final var parentEntity = multiReference.getOriParentEntity();
		
		return new EntityUpdateDTO(
			parentEntity.getId(),
			parentEntity.getSaveStamp(),
			new ContentFieldDTO(multiReference.getName())
		);
	}
	
	//method
	private boolean canAddEntity(final IMultiReference<?> multiReference) {
		return
		multiReference != null
		&& multiReference.belongsToEntity()
		&& multiReference.getOriParentEntity().isOpen();
	}
	
	//method
	private boolean canRemoveEntity(IMultiReference<?> multiReference) {
		return
		multiReference != null
		&& multiReference.isOpen();
	}
}
