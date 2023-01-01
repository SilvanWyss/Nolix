//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;

//class
final class EntityMapper {
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//method
	@SuppressWarnings("unchecked")
	public <E extends IEntity<DataImplementation>> E createLoadedEntityFromDTO(
		final ILoadedEntityDTO loadedEntityDTO,
		final ITable<DataImplementation, E> table
	) {
		
		final var loadedEntity = entityCreator.createEmptyEntityFor(table);
		
		final var concreteEntity = (BaseEntity)loadedEntity;
		concreteEntity.internalSetParentTable((ITable<DataImplementation, IEntity<DataImplementation>>)table);
		concreteEntity.internalSetLoaded();
		addDataToEntityFromLoadedEntityDTO(loadedEntityDTO, concreteEntity);
		
		return loadedEntity;
	}
	
	//method
	private void addDataFromContentFieldToEntity(final ILoadedContentFieldDTO contentField, final BaseEntity entity) {
		addDataFromContentFieldToProperty(
			contentField,
			entity.internalGetRefPropertyByName(contentField.getColumnName())
		);
	}
	
	//method
	private void addDataFromContentFieldToProperty(final ILoadedContentFieldDTO contentField, final Property property) {
		property.internalSetOrClearDirectlyFromContent(contentField.getValueOrNull());
	}
	
	//method
	private void addDataToEntityFromLoadedEntityDTO(final ILoadedEntityDTO loadedEntityDTO, final BaseEntity entity) {
		
		entity.internalSetId(loadedEntityDTO.getId());
		entity.internalSetSaveStamp(loadedEntityDTO.getSaveStamp());
		
		for (final var cf : loadedEntityDTO.getContentFields()) {
			addDataFromContentFieldToEntity(cf, entity);
		}
	}
}
