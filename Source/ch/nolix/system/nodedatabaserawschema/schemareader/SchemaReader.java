//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.SaveStampStrategy;

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
		
		GlobalValidator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
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
	public int getTableCount() {
		return databaseNodeSearcher.getTableNodeCount(databaseNode);
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableId(final String tableId) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableIdFromDatabaseNode(databaseNode, tableId);
		
		return
		tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableName(final String tableName) {
		
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
	public IContainer<IFlatTableDTO> loadFlatTables() {
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
	public IContainer<ITableDTO> loadTables() {
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
		databasePropertiesNodeSearcher.getRefSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(timestampNode);
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	private IContainer<IColumnDTO> loadColumnsFromTableNode(final BaseNode tableNode) {
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
