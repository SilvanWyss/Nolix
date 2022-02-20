//package declaration
package ch.nolix.system.noderawobjectschema.schemareader;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.noderawobjectschema.structure.ColumnNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.SaveStampStrategy;

//class
public final class SchemaReader implements ISchemaReader {
	
	//static attribute
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	
	//static attribute
	private static final DatabasePropertiesNodeSearcher databasePropertiesNodeSearcher =
	new DatabasePropertiesNodeSearcher();
	
	//static attribute
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	
	//static attribute
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//static attribute
	private static final FlatTableDTOMapper flatTableDTOMapper = new FlatTableDTOMapper();
	
	//static attribute
	private static final ColumnDTOMapper columnDTOMapper = new ColumnDTOMapper();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final BaseNode databaseNode;
	
	//constructor
	public SchemaReader(final BaseNode databaseNode) {
		
		Validator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
	}
	
	//method
	@Override
	public boolean columnIsEmpty(String tableName, String columnName) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);
		
		final var columnNode = tableNodeSearcher.getRefColumnNodeFromTableNodeByColumnName(tableNode, columnName);
		
		return columnNodeSearcher.columnNodeContainsEntityNode(columnNode);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumnsByTableId(final String tableId) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableIdFromDatabaseNode(databaseNode, tableId);
		
		return
		tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumnsByTableName(final String tableName) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);
		
		return
		tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(final String id) {
		return
		flatTableDTOMapper.createFlatTableDTOFromTableNode(
			databaseNodeSearcher.getRefTableNodeByTableIdFromDatabaseNode(databaseNode, id)
		);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableByName(final String name) {
		return
		flatTableDTOMapper.createFlatTableDTOFromTableNode(
			databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, name)
		);
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return
		databaseNodeSearcher
		.getRefTableNodesFromDatabaseNode(databaseNode)
		.to(flatTableDTOMapper::createFlatTableDTOFromTableNode);
	}
	
	//method
	@Override
	public ITableDTO loadTableById(final String id) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableIdFromDatabaseNode(databaseNode, id);
		
		return loadTableFromTableNode(tableNode);
	}
	
	//method
	@Override
	public ITableDTO loadTableByName(final String name) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(databaseNode, name);
		
		return loadTableFromTableNode(tableNode);
	}
	
	//method
	@Override
	public LinkedList<ITableDTO> loadTables() {
		return
		databaseNodeSearcher
		.getRefTableNodesFromDatabaseNode(databaseNode)
		.to(this::loadTableFromTableNode);
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getRefDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		final var timestampNode =
		databasePropertiesNodeSearcher.getSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(timestampNode);
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	private LinkedList<IColumnDTO> loadColumnsFromTableNode(final BaseNode tableNode) {
		return
		tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	private ITableDTO loadTableFromTableNode(final BaseNode tableNode) {
		return
		new TableDTO(
			tableNodeSearcher.getRefIdNodeFromTableNode(tableNode).getOneAttributeHeader(),
			tableNodeSearcher.getRefNameNodeFromTableNode(tableNode).getOneAttributeHeader(),
			new SaveStampConfigurationDTO(SaveStampStrategy.OWN_SAVE_STAMP),
			loadColumnsFromTableNode(tableNode)
		);
	}
}
