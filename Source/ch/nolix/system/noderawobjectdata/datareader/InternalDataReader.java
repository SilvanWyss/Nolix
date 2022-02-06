//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.structure.RecordNodeSearcher;
import ch.nolix.system.noderawobjectdata.structure.TableNodeSearcher;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.sqlrawobjectdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class InternalDataReader {
		
	//static attribute
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final RecordNodeSearcher recordNodeSearcher = new RecordNodeSearcher();
		
	//static attribute
	private static final LoadedRecordDTOMapper loadedRecordDTOMapper = new LoadedRecordDTOMapper();
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
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
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		return
		tableNodeSearcher
		.getRefRecordNodesFromTableNode(tableNode)
		.to(rn -> loadedRecordDTOMapper.createLoadedRecordDTOFromRecordNode(rn, tableInfo));
	}
	
	//method
	public LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final TableInfo tableInfo,
		final String recordId,
		final String multiReferenceColumnName
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiReferenceColumnName);
		
		final var multiValueNode =
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
		
		return multiValueNode.getHeadersOfAttributes();
		
	}
	
	//method
	public LinkedList<Object> loadMultiValueEntriesFromRecord(
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, recordId);
		
		final var multiValueColumnIndex = tableInfo.getIndexOfColumnByColumnName(multiValueColumnName);
		
		final var multiValueNode =
		recordNodeSearcher.getRefContentFieldNodeFromRecordNodeAtIndex(recordNode, multiValueColumnIndex);
		
		final var columnInfo = tableInfo.getColumnInfoByColumnName(multiValueColumnName);
		
		return multiValueNode.getRefAttributes().to(a -> valueMapper.createValueFromString(a.getHeader(), columnInfo));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final TableInfo tableInfo, final String id) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
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
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var valueIndex = 2 + tableInfo.getIndexOfColumnByColumnName(columnName);
		
		return
		tableNodeSearcher.tableNodeContainsRecordNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, valueIndex, value);
	}
}
