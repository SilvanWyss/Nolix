//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
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
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final TableInfo tableInfo) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getName());
		
		return
		tableNodeSearcher
		.getRefRecordNodesFromTableNode(tableNode)
		.to(rn -> loadedRecordDTOMapper.createLoadedRecordDTOFromRecordNode(rn, tableInfo));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final TableInfo tableInfo, final String id) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, id);
		
		return loadedRecordDTOMapper.createLoadedRecordDTOFromRecordNode(recordNode, tableInfo);
	}
	
	//method
	public boolean tableContainsRecordWithGivenValueAtColumn(
		final TableInfo tableInfo,
		final String columnName,
		final String value
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getName());
		
		final var valueIndex = 2 + tableInfo.getIndexOfContentColumnWithName(columnName);
		
		return
		tableNodeSearcher.tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, valueIndex, value);
	}
}
