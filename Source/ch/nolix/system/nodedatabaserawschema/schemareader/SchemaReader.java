//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.system.time.moment.Time;
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
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final IMutableNode<?> databaseNode;
	
	//constructor
	public SchemaReader(final IMutableNode<?> databaseNode) {
		
		GlobalValidator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
	}
	
	//method
	@Override
	public boolean columnIsEmpty(String tableName, String columnName) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);
		
		final var columnNode = tableNodeSearcher.getOriColumnNodeFromTableNodeByColumnName(tableNode, columnName);
		
		return columnNodeSearcher.columnNodeContainsEntityNode(columnNode);
	}
	
	//method
	@Override
	public CloseController getOriCloseController() {
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
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableIdFromDatabaseNode(databaseNode, tableId);
		
		return
		tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableName(final String tableName) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);
		
		return
		tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(final String id) {
		return
		flatTableDTOMapper.createFlatTableDTOFromTableNode(
			databaseNodeSearcher.getOriTableNodeByTableIdFromDatabaseNode(databaseNode, id)
		);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableByName(final String name) {
		return
		flatTableDTOMapper.createFlatTableDTOFromTableNode(
			databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, name)
		);
	}
	
	//method
	@Override
	public IContainer<IFlatTableDTO> loadFlatTables() {
		return
		databaseNodeSearcher
		.getOriTableNodesFromDatabaseNode(databaseNode)
		.to(flatTableDTOMapper::createFlatTableDTOFromTableNode);
	}
	
	//method
	@Override
	public ITableDTO loadTableById(final String id) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableIdFromDatabaseNode(databaseNode, id);
		
		return loadTableFromTableNode(tableNode);
	}
	
	//method
	@Override
	public ITableDTO loadTableByName(final String name) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, name);
		
		return loadTableFromTableNode(tableNode);
	}
	
	//method
	@Override
	public IContainer<ITableDTO> loadTables() {
		return
		databaseNodeSearcher
		.getOriTableNodesFromDatabaseNode(databaseNode)
		.to(this::loadTableFromTableNode);
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getOriDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		final var timestampNode =
		databasePropertiesNodeSearcher.getOriSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(timestampNode);
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	private IContainer<IColumnDTO> loadColumnsFromTableNode(final IMutableNode<?> tableNode) {
		return
		tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	private ITableDTO loadTableFromTableNode(final IMutableNode<?> tableNode) {
		return
		new TableDTO(
			tableNodeSearcher.getOriIdNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
			tableNodeSearcher.getOriNameNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
			new SaveStampConfigurationDTO(SaveStampStrategy.OWN_SAVE_STAMP),
			loadColumnsFromTableNode(tableNode)
		);
	}
}
