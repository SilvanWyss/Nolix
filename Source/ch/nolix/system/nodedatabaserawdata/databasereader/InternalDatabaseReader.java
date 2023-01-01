//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.sqlrawdata.databasereader.ValueMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class InternalDatabaseReader {
		
	//static attribute
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	
	//static attribute
	private static final DatabasePropertiesNodeSearcher databasePropertiesNodeSearcher =
	new DatabasePropertiesNodeSearcher();
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final LoadedEntityDTOMapper loadedEntityDTOMapper = new LoadedEntityDTOMapper();
	
	//static attribute
	private static final ValueMapper valueMapper = new ValueMapper();
	
	//attribute
	private final IMutableNode<?> databaseNode;
	
	//constructor
	public InternalDatabaseReader(final IMutableNode<?> databaseNode) {
		
		GlobalValidator.assertThat(databaseNode).thatIsNamed("database node").isNotNull();
		
		this.databaseNode = databaseNode;
	}
	
	//method
	public Time getSchemaTimestamp() {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getRefDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		return databasePropertiesNodeSearcher.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
	}
	
	//method
	public IContainer<ILoadedEntityDTO> loadAllRecordsFromTable(final ITableInfo tableInfo) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		return
		tableNodeSearcher
		.getRefRecordNodesFromTableNode(tableNode)
		.to(rn -> loadedEntityDTOMapper.createLoadedRecordDTOFromRecordNode(rn, tableInfo));
	}
	
	//method
	public IContainer<String> loadAllMultiReferenceEntriesForRecord(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueNode = entityNode.getRefChildNodeAt1BasedIndex(multiReferenceColumnIndex);
		
		return multiValueNode.getChildNodesHeaders();
	}
	
	//method
	public IContainer<Object> loadMultiValueEntriesFromRecord(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo
	) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var entityNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, entityId);
		
		final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();
		
		final var multiValueNode = entityNode.getRefChildNodeAt1BasedIndex(multiValueColumnIndex);
		
		return
		multiValueNode
		.getRefChildNodes()
		.to(a -> valueMapper.createValueFromString(a.getHeader(), multiValueColumnInfo));
	}
	
	//method
	public ILoadedEntityDTO loadRecordFromTableById(final ITableInfo tableInfo, final String id) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableInfo.getTableName());
		
		final var recordNode = tableNodeSearcher.getRefRecordNodeFromTableNode(tableNode, id);
		
		return loadedEntityDTOMapper.createLoadedRecordDTOFromRecordNode(recordNode, tableInfo);
	}
	
	//method
	public boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final ITableInfo tableInfo,
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
