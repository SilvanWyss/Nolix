//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;

//class
final class EntityMapper {
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//method
	@SuppressWarnings("unchecked")
	public <E extends IEntity<DataImplementation>> E createEntityFromRecordForGivenTable(
		final ILoadedRecordDTO record,
		final ITable<DataImplementation, E> table
	) {
		
		final var entity = entityCreator.createEmptyEntityFor(table);
		
		final var concreteEntity = (BaseEntity)entity;
		concreteEntity.internalSetParentTable((ITable<DataImplementation, IEntity<DataImplementation>>)table);
		concreteEntity.internalSetLoaded();
		addDataFromRecordToEntity(record, concreteEntity);
		
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
	private void addDataFromRecordToEntity(final ILoadedRecordDTO record, final BaseEntity entity) {
		
		entity.internalSetId(record.getId());
		entity.internalSetSaveStamp(record.getId());
		
		for (final var cf : record.getContentFields()) {
			addDataFromContentFieldToEntity(cf, entity);
		}
	}
}
