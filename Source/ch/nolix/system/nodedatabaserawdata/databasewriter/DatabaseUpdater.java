//package declaration
package ch.nolix.system.nodedatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class DatabaseUpdater {
	
	//static attribute
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	
	//static attribute
	private static final DatabasePropertiesNodeSearcher databasePropertiesNodeSearcher =
	new DatabasePropertiesNodeSearcher();
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final EntityNodeSearcher entityNodeSearcher = new EntityNodeSearcher();
	
	//static attribute
	private static final EntityNodeMapper entityNodeMapper = new EntityNodeMapper();
	
	//method
	public void deleteEntriesFromMultiReference(
		final IMutableNode<?> databaseNode,
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiReferenceColumnNode = entityNode.getRefChildNodeAt1BasedIndex(multiReferenceColumnIndex);
		
		multiReferenceColumnNode.removeChildNodes();
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final IMutableNode<?> databaseNode,
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueColumnNode = entityNode.getRefChildNodeAt1BasedIndex(multiValueColumnIndex);
		
		multiValueColumnNode.removeChildNodes();
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final IMutableNode<?> databaseNode,
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferencedColumnInfo,
		final String referencedEntityId
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = multiReferencedColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiReferenceColumnNode = entityNode.getRefChildNodeAt1BasedIndex(multiReferenceColumnIndex);
		
		multiReferenceColumnNode.removeFirstChildNodeWithHeader(referencedEntityId);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final IMutableNode<?> databaseNode,
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo,
		final String entry
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueColumnNode = entityNode.getRefChildNodeAt1BasedIndex(multiValueColumnIndex);
		
		multiValueColumnNode.removeFirstChildNodeWithHeader(entry);
	}
	
	//method
	public void deleteRecordFromTable(
		final IMutableNode<?> database,
		final String tableName,
		final IEntityHeadDTO entity
	) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableName);
		
		final var recordNode =
		tableNodeSearcher.removeAndGetRefRecordNodeFromTableNode(tableNode, entity.getId());
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		if (!saveStampNode.hasHeader(entity.getSaveStamp())) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
	}
	
	//method
	public void expectGivenSchemaTimestamp(final IMutableNode<?> databaseNode, final ITime schemaTimestamp) {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getRefDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		final var actualSchemaTimestamp =
		databasePropertiesNodeSearcher.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
		
		if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("schema");
		}
	}
	
	//method
	public void expectTableContainsEntity(
		final IMutableNode<?> databaseNode,
		final String tableName,
		final String entityId
	) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);
		
		final var containsEntity = tableNodeSearcher.tableNodeContainsRecordNodeWhoseFieldAtGivenIndexContainsGivenValue(
			tableNode,
			FieldIndexCatalogue.ID_INDEX,
			entityId
		);
		
		if (!containsEntity) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				LowerCaseCatalogue.DATABASE,
				databaseNode,
				"does not contain a " + tableName + " with the id " + entityId
			);
		}
	}
	
	//method
	public void insertEntryIntoMultiReference(
		final IMutableNode<?> databaseNode,
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo,
		final String referencedEntityId
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiReferenceColumnNode = entityNode.getRefChildNodeAt1BasedIndex(multiReferenceColumnIndex);
		
		multiReferenceColumnNode.addChildNode(Node.withHeader(referencedEntityId));
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final IMutableNode<?> databaseNode,
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo,
		final String entry
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueColumnNode = entityNode.getRefChildNodeAt1BasedIndex(multiValueColumnIndex);
		
		multiValueColumnNode.addChildNode(Node.withHeader(entry));
	}
	
	//method
	public void insertRecordIntoTable(
		final IMutableNode<?> database,
		final ITableInfo tableInfo,
		final INewEntityDTO newEntity
	) {
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableInfo.getTableName());
		
		if (tableNodeSearcher.tableNodeContainsRecordNodeWithGivenId(tableNode, newEntity.getId())) {
			throw
			ArgumentHasAttributeException.forArgumentAndAttributeName(
				"table " + tableInfo.getTableNameInQuotes(),
				"record with the id '" + newEntity.getId() + "'"
			);
		}
		
		final var recordNode = entityNodeMapper.createNodeFromRecordWithSaveStamp(tableInfo, newEntity, 0);
		
		tableNode.addChildNode(recordNode);
	}
	
	//method
	public void setEntityAsUpdated(final IMutableNode<?> database, final String tableName, final IEntityHeadDTO entity) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableName);
		
		final var entityNode = tableNodeSearcher.getRefEntityNodeFromTableNodeOrNull(tableNode, entity.getId());
		if (entityNode == null) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(entityNode);
		
		final var saveStamp = saveStampNode.getHeader();
		if (!saveStamp.equals(entity.getSaveStamp())) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
		saveStampNode.setHeader(newSaveStamp);
	}
	
	//method
	public void updateEntityOnTable(
		final IMutableNode<?> database,
		final ITableInfo tableInfo,
		final IEntityUpdateDTO entityUpdate
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefEntityNodeFromTableNodeOrNull(tableNode, entityUpdate.getId());
		if (entityNode == null) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(entityNode);
		
		final var saveStamp = saveStampNode.getHeader();
		if (!saveStamp.equals(entityUpdate.getSaveStamp())) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
		saveStampNode.setHeader(newSaveStamp);
		
		updateEntityNode(entityNode, tableInfo, entityUpdate);
	}
	
	//method
	private void updateEntityNode(
		final IMutableNode<?> recordNode,
		final ITableInfo tableInfo,
		final IEntityUpdateDTO recordUdate
	) {
		for (final var ucf : recordUdate.getUpdatedContentFields()) {
			
			final var columnInfo = tableInfo.getColumnInfoByColumnName(ucf.getColumnName());
			final var columnIndex = columnInfo.getColumnIndexOnEntityNode();
			final var contentFieldNode = recordNode.getRefChildNodeAt1BasedIndex(columnIndex);
						
			final var value = ucf.getValueAsStringOrNull();
			if (value == null) {
				contentFieldNode.removeHeader();
			} else {
				contentFieldNode.setHeader(value);
			}
		}
	}
}
