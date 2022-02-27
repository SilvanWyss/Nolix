//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//class
final class DatabaseUpdater {
	
	//static attributes
	private static final EntityNodeMapper entityNodeMapper = new EntityNodeMapper();
	
	//static attributes
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	private static final EntityNodeSearcher entityNodeSearcher = new EntityNodeSearcher();
	
	//method
	public void deleteEntriesFromMultiReference(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String entityId,
		final String multiReferenceColumnName
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiReferenceColumnName);
		
		final var multiReferenceColumnNode =
		entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(entityNode, multiReferenceColumnIndex);
		
		multiReferenceColumnNode.removeAttributes();
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String entityId,
		final String multiValueColumnName
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueColumnNode =
		entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
						
		multiValueColumnNode.removeAttributes();
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String entityId,
		final String multiReferencedColumnName,
		final String referencedEntityId
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiReferencedColumnName);
		
		final var multiReferenceColumnNode =
		entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(entityNode, multiReferenceColumnIndex);
		
		multiReferenceColumnNode.removeFirstAttribute(referencedEntityId);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueColumnNode =
		entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
		
		multiValueColumnNode.removeFirstAttribute(entry);
	}
	
	//method
	public void deleteRecordFromTable(
		final BaseNode database,
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
	public void insertEntryIntoMultiReference(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiReferenceColumnName);
		
		final var multiReferenceColumnNode =
		entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(entityNode, multiReferenceColumnIndex);
		
		multiReferenceColumnNode.addAttribute(Node.withHeader(referencedEntityId));
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueColumnNode =
		entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
		
		multiValueColumnNode.addAttribute(Node.withHeader(entry));
	}
	
	//method
	public void insertRecordIntoTable(
		final BaseNode database,
		final TableInfo tableInfo,
		final IRecordDTO record
	) {
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableInfo.getTableName());
		
		if (tableNodeSearcher.tableNodeContainsRecordNodeWithId(tableNode, record.getId())) {
			throw
			new ArgumentHasAttributeException(
				"table " + tableInfo.getTableNameInQuotes(),
				"record with the id '" + record.getId() + "'"
			);
		}
		
		final var recordNode = entityNodeMapper.createNodeFromRecordWithSaveStamp(tableInfo, record, 0);
		
		tableNode.addAttribute(recordNode);
	}
	
	//method
	public void setEntityAsUpdated(final BaseNode database, final String tableName, final IEntityHeadDTO entity) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableName);
		
		final var entityNode = tableNodeSearcher.getRefEntityNodeFromTableNodeOrNull(tableNode, entity.getId());
		if (entityNode == null) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		final var saveStampNode = entityNodeSearcher.getRefSaveStampNodeFromRecordNode(entityNode);
		final var saveStampValueNode = saveStampNode.getRefOneAttribute();
		
		final var saveStamp = saveStampValueNode.getHeader();
		if (!saveStamp.equals(entity.getSaveStamp())) {
			throw ResourceWasChangedInTheMeanwhileException.forResource("data");
		}
		
		saveStampValueNode.setHeader(String.valueOf(Integer.valueOf(saveStamp) + 1));
	}
	
	//method
	public void updateRecordOnTable(
		final BaseNode database,
		final TableInfo tableInfo,
		final IRecordUpdateDTO recordUdate
	) {
	
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordUdate.getId());
		
		updateEntityNode(recordNode, tableInfo, recordUdate);
	}
	
	//method
	private void updateEntityNode(
		final BaseNode recordNode,
		final TableInfo tableInfo,
		final IRecordUpdateDTO recordUdate
	) {
		for (final var ucf : recordUdate.getUpdatedContentFields()) {
			
			final var contentFieldNode =
			entityNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(
				recordNode,
				tableInfo.getIndexOfColumnByColumnName(ucf.getColumnName())
			);
			
			final var value = ucf.getValueAsStringOrNull();
			if (value == null) {
				contentFieldNode.removeHeader();
			} else {
				contentFieldNode.setHeader(value);
			}
		}
	}
}
