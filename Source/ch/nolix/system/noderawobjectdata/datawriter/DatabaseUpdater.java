//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.exception.GeneralException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.system.noderawobjectdata.structure.RecordNodeSearcher;
import ch.nolix.system.noderawobjectdata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.recorddtoapi.IRecordUpdateDTO;

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
		
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(database, tableName);
		final var recordNode = tableNodeSearcher.removeAndGetRefRecordNodeFromTableNode(tableNode, recordDeletion.getId());
		final var saveStampNode = recordNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		if (!saveStampNode.hasHeader(recordDeletion.getSaveStamp())) {
			throw new GeneralException("The data was changed in the meanwhile.");
		}
	}
	
	//method
	public void insertRecordIntoTable(
		final BaseNode database,
		final TableDefinition tableDefinition,
		final IRecordDTO record
	) {
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(database, tableDefinition.getName());
		
		if (tableNodeSearcher.tableNodeContainsRecordNodeWithId(tableNode, record.getId())) {
			throw
			new ArgumentHasAttributeException(
				"table " + tableDefinition.getNameInQuotes(),
				"record with the id '" + record.getId() + "'"
			);
		}
		
		final var recordNode = recordNodeMapper.createNodeFromRecordWithSaveStamp(tableDefinition, record, 0);
		
		tableNode.addAttribute(recordNode);
	}
	
	//method
	public void updateRecordOnTable(
		final BaseNode database,
		final TableDefinition tableDefinition,
		final IRecordUpdateDTO recordUdate
	) {
	
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(database, tableDefinition.getName());
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordUdate.getId());
		final var saveStampNode = recordNodeSearcher.getRefSaveStampNodeFromRecordNode(recordNode);
		
		if (!saveStampNode.hasHeader(recordUdate.getSaveStamp())) {
			throw new GeneralException("The data was changed in the meanwhile.");
		}
		
		updateRecordNodeFromRecordWhenValidated(recordNode, tableDefinition, recordUdate);
	}
	
	//method
	private void updateRecordNodeFromRecordWhenValidated(
		final BaseNode recordNode,
		final TableDefinition tableDefinition,
		final IRecordUpdateDTO recordUdate
	) {
		for (final var ucf : recordUdate.getUpdatedContentFields()) {
			
			final var contentFieldNode =
			recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(
				recordNode,
				tableDefinition.getIndexOfContentColumnWithHeader(ucf.getColumnHeader())
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
