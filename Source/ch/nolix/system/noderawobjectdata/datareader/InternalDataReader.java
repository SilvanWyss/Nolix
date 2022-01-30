//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class InternalDataReader {
	
	//static attributes
	private static final LoadedRecordDTOMapper loadedRecordDTOMapper = new LoadedRecordDTOMapper();
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//attribute
	private final BaseNode databaseNode;
	
	//constructor
	public InternalDataReader(final BaseNode databaseNode) {
		
		Validator.assertThat(databaseNode).thatIsNamed("database node").isNotNull();
		
		this.databaseNode = databaseNode;
	}
	
	//class
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final TableDefinition tableDefinition) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableDefinition.getName());
		
		return
		tableNodeSearcher
		.getRefRecordNodesFromTableNode(tableNode)
		.to(rn -> loadedRecordDTOMapper.createLoadedRecordDTOFromRecordNode(rn, tableDefinition));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final TableDefinition tableDefinition, final String id) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableDefinition.getName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, id);
		
		return loadedRecordDTOMapper.createLoadedRecordDTOFromRecordNode(recordNode, tableDefinition);
	}
	
	//method
	public boolean tableContainsRecordWithGivenValueAtColumn(
		final TableDefinition tableDefinition,
		final String columnName,
		final String value
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableDefinition.getName());
		
		final var valueIndex = 2 + tableDefinition.getIndexOfContentColumnWithName(columnName);
		
		return
		tableNodeSearcher.tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, valueIndex, value);
	}
}
