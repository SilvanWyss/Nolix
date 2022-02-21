//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.system.noderawobjectdata.structure.RecordNodeSearcher;
import ch.nolix.system.noderawobjectdata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
final class DatabaseUpdater {
	
	//static attributes
	private static final RecordNodeMapper recordNodeMapper = new RecordNodeMapper();
	
	//static attributes
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	private static final RecordNodeSearcher recordNodeSearcher = new RecordNodeSearcher();
	
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
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(entityNode, multiReferenceColumnIndex);
		
		multiReferenceColumnNode.removeAttributes();
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueColumnNode =
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
						
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
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(entityNode, multiReferenceColumnIndex);
		
		multiReferenceColumnNode.removeFirstAttribute(referencedEntityId);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueColumnNode =
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
		
		multiValueColumnNode.removeFirstAttribute(entry);
	}
	
	//method
	public void deleteRecordFromTable(
		final BaseNode database,
		final String tableName,
		final IEntityHeadDTO recordHead
	) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableName);
		
		final var recordNode =
		tableNodeSearcher.removeAndGetRefRecordNodeFromTableNode(tableNode, recordHead.getId());
		
		final var saveStampNode = recordNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		if (!saveStampNode.hasHeader(recordHead.getSaveStamp())) {
			throw new GeneralException("The data was changed in the meanwhile.");
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
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(entityNode, multiReferenceColumnIndex);
		
		multiReferenceColumnNode.addAttribute(Node.withHeader(referencedEntityId));
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final BaseNode databaseNode,
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueColumnNode =
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
		
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
		
		final var recordNode = recordNodeMapper.createNodeFromRecordWithSaveStamp(tableInfo, record, 0);
		
		tableNode.addAttribute(recordNode);
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
		final var saveStampNode = recordNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		if (!saveStampNode.hasHeader(recordUdate.getSaveStamp())) {
			throw new GeneralException("The data was changed in the meanwhile.");
		}
		
		updateRecordNodeFromRecordWhenValidated(recordNode, tableInfo, recordUdate);
	}
	
	//method
	private void updateRecordNodeFromRecordWhenValidated(
		final BaseNode recordNode,
		final TableInfo tableInfo,
		final IRecordUpdateDTO recordUdate
	) {
		for (final var ucf : recordUdate.getUpdatedContentFields()) {
			
			final var contentFieldNode =
			recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(
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
