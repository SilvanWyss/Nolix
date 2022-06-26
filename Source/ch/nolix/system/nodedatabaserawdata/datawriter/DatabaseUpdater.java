//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
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
		final BaseNode<?> databaseNode,
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
		final BaseNode<?> databaseNode,
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
		final BaseNode<?> databaseNode,
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
		final BaseNode<?> databaseNode,
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
		final BaseNode<?> database,
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
	public void expectGivenSchemaTimestamp(final BaseNode<?> databaseNode, final ITime schemaTimestamp) {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getRefDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		final var actualSchemaTimestamp =
		databasePropertiesNodeSearcher.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
		
		if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("schema");
		}
	}
	
	//method
	public void insertEntryIntoMultiReference(
		final BaseNode<?> databaseNode,
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
		final BaseNode<?> databaseNode,
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
		final BaseNode<?> database,
		final ITableInfo tableInfo,
		final IRecordDTO pRecord
	) {
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableInfo.getTableName());
		
		if (tableNodeSearcher.tableNodeContainsRecordNodeWithGivenId(tableNode, pRecord.getId())) {
			throw
			ArgumentHasAttributeException.forArgumentAndAttributeName(
				"table " + tableInfo.getTableNameInQuotes(),
				"record with the id '" + pRecord.getId() + "'"
			);
		}
		
		final var recordNode = entityNodeMapper.createNodeFromRecordWithSaveStamp(tableInfo, pRecord, 0);
		
		tableNode.addChildNode(recordNode);
	}
	
	//method
	public void setEntityAsUpdated(final BaseNode<?> database, final String tableName, final IEntityHeadDTO entity) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableName);
		
		final var entityNode = tableNodeSearcher.getRefEntityNodeFromTableNodeOrNull(tableNode, entity.getId());
		if (entityNode == null) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(entityNode);
		final var saveStampValueNode = saveStampNode.getRefSingleChildNode();
		
		final var saveStamp = saveStampValueNode.getHeader();
		if (!saveStamp.equals(entity.getSaveStamp())) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		saveStampValueNode.setHeader(String.valueOf(Integer.valueOf(saveStamp) + 1));
	}
	
	//method
	public void updateRecordOnTable(
		final BaseNode<?> database,
		final ITableInfo tableInfo,
		final IRecordUpdateDTO recordUdate
	) {
	
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordUdate.getId());
		
		updateEntityNode(recordNode, tableInfo, recordUdate);
	}
	
	//method
	private void updateEntityNode(
		final BaseNode<?> recordNode,
		final ITableInfo tableInfo,
		final IRecordUpdateDTO recordUdate
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
