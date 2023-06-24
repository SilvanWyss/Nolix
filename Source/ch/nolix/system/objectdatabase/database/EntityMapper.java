//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;

//class
final class EntityMapper {
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//method
	@SuppressWarnings("unchecked")
	public <E extends IEntity> E createLoadedEntityFromDTO(
		final ILoadedEntityDto loadedEntityDto,
		final ITable<E> table
	) {
		
		final var loadedEntity = entityCreator.createEmptyEntityFor(table);
		
		final var concreteEntity = (BaseEntity)loadedEntity;
		concreteEntity.internalSetParentTable((ITable<IEntity>)table);
		concreteEntity.internalSetLoaded();
		addDataToEntityFromLoadedEntityDTO(loadedEntityDto, concreteEntity);
		
		return loadedEntity;
	}
	
	//method
	private void addDataFromContentFieldToEntity(final ILoadedContentFieldDto contentField, final BaseEntity entity) {
		addDataFromContentFieldToProperty(
			contentField,
			entity.internalGetRefPropertyByName(contentField.getColumnName())
		);
	}
	
	//method
	private void addDataFromContentFieldToProperty(final ILoadedContentFieldDto contentField, final Property property) {
		property.internalSetOrClearDirectlyFromContent(contentField.getValueOrNull());
	}
	
	//method
	private void addDataToEntityFromLoadedEntityDTO(final ILoadedEntityDto loadedEntityDto, final BaseEntity entity) {
		
		entity.internalSetId(loadedEntityDto.getId());
		entity.internalSetSaveStamp(loadedEntityDto.getSaveStamp());
		
		for (final var cf : loadedEntityDto.getContentFields()) {
			addDataFromContentFieldToEntity(cf, entity);
		}
	}
}
