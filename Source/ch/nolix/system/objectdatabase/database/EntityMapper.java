//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedRecordDTO;

//class
final class EntityMapper {
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//method
	@SuppressWarnings("unchecked")
	public <E extends IEntity<DataImplementation>> E createEntityFromRecordForGivenTable(
		final ILoadedRecordDTO pRecord,
		final ITable<DataImplementation, E> table
	) {
		
		final var entity = entityCreator.createEmptyEntityFor(table);
		
		final var concreteEntity = (BaseEntity)entity;
		concreteEntity.internalSetParentTable((ITable<DataImplementation, IEntity<DataImplementation>>)table);
		concreteEntity.internalSetLoaded();
		addDataFromRecordToEntity(pRecord, concreteEntity);
		
		return entity;
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
	private void addDataFromRecordToEntity(final ILoadedRecordDTO pRecord, final BaseEntity entity) {
		
		entity.internalSetId(pRecord.getId());
		entity.internalSetSaveStamp(pRecord.getId());
		
		for (final var cf : pRecord.getContentFields()) {
			addDataFromContentFieldToEntity(cf, entity);
		}
	}
}
