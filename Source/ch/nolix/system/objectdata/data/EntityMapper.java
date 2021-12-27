//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.reflectionhelper.GlobalClassHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//class
final class EntityMapper {
	
	//method
	@SuppressWarnings("unchecked")
	public <E extends BaseEntity> E createEntityFromRecordForGivenTable(
		final ILoadedRecordDTO record,
		final Table<E> table
	) {
		
		final var entity = createEmptyEntityFor(table);
		
		entity.internalSetParentTable((Table<IEntity<DataImplementation>>)table);
		entity.internalSetLoaded();
		addDataFromRecordToEntity(record, entity);
		
		return entity;
	}
	
	//method
	private void addDataFromContentFieldToEntity(final IContentFieldDTO contentField, final BaseEntity entity) {
		addDataFromContentFieldToProperty(
			contentField,
			entity.internalGetRefPropertyByName(contentField.getColumnHeader())
		);
	}
	
	//method
	private void addDataFromContentFieldToProperty(final IContentFieldDTO contentField, final Property property) {
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
	
	//method
	private <E extends BaseEntity> E createEmptyEntityFor(final Table<E> table) {
		return createEmptyEntityOfEntityClass(table.getEntityClass());
	}
	
	//method
	private <E extends BaseEntity> E createEmptyEntityOfEntityClass(final Class<E> entityClass) {
		return GlobalClassHelper.createInstanceFromDefaultConstructorOf(entityClass);
	}
}
