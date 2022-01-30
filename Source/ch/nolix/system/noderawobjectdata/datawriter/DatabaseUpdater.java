//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.system.noderawobjectdata.structure.RecordNodeSearcher;
import ch.nolix.system.noderawobjectdata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
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
	public void deleteRecordFromTable(
		final BaseNode database,
		final String tableName,
		final IRecordDeletionDTO recordDeletion
	) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(database, tableName);
		
		final var recordNode =
		tableNodeSearcher.removeAndGetRefRecordNodeFromTableNode(tableNode, recordDeletion.getId());
		
		final var saveStampNode = recordNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		if (!saveStampNode.hasHeader(recordDeletion.getSaveStamp())) {
			throw new GeneralException("The data was changed in the meanwhile.");
		}
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
			
			final var value = ucf.getValueOrNull();
			if (value == null) {
				contentFieldNode.removeHeader();
			} else {
				contentFieldNode.setHeader(value.toString());
			}
		}
	}
}
