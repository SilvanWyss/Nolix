//package declaration
package ch.nolix.system.nodedatabaserawdata.datareader;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.sqlrawdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class InternalDataReader {
		
	//static attribute
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	
	//static attribute
	private static final DatabasePropertiesNodeSearcher databasePropertiesNodeSearcher =
	new DatabasePropertiesNodeSearcher();
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
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
	
	//method
	public Time getSchemaTimestamp() {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getRefDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		return databasePropertiesNodeSearcher.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
	}
	
	//method
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
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueNode = entityNode.getRefAttributeAt(multiReferenceColumnIndex);
		
		return multiValueNode.getHeadersOfAttributes();
	}
	
	//method
	public LinkedList<Object> loadMultiValueEntriesFromRecord(
		final TableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueNode = entityNode.getRefAttributeAt(multiValueColumnIndex);
		
		return
		multiValueNode
		.getRefAttributes()
		.to(a -> valueMapper.createValueFromString(a.getHeader(), multiValueColumnInfo));
	}
	
	//method
	public ILoadedRecordDTO loadRecordFromTableById(final TableInfo tableInfo, final String id) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, id);
		
		return loadedRecordDTOMapper.createLoadedRecordDTOFromRecordNode(recordNode, tableInfo);
	}
	
	//method
	public boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final TableInfo tableInfo,
		final IColumnInfo columnInfo,
		final String value
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var columnIndex = columnInfo.getColumnIndexOnEntityNode();
		
		return
		tableNodeSearcher.tableNodeContainsRecordNodeWhoseFieldAtGivenIndexContainsGivenValue(
			tableNode,
			columnIndex,
			value
		);
	}
}
