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
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDto;
import ch.nolix.system.objectschema.schemadto.TableDto;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
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
	private static final FlatTableDtoMapper flatTableDtoMapper = new FlatTableDtoMapper();
	
	//static attribute
	private static final ColumnDtoMapper columnDtoMapper = new ColumnDtoMapper();
	
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
	public IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableIdFromDatabaseNode(databaseNode, tableId);
		
		return
		tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode).to(columnDtoMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);
		
		return
		tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode).to(columnDtoMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public IFlatTableDto loadFlatTableById(final String id) {
		return
		flatTableDtoMapper.createFlatTableDTOFromTableNode(
			databaseNodeSearcher.getOriTableNodeByTableIdFromDatabaseNode(databaseNode, id)
		);
	}
	
	//method
	@Override
	public IFlatTableDto loadFlatTableByName(final String name) {
		return
		flatTableDtoMapper.createFlatTableDTOFromTableNode(
			databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, name)
		);
	}
	
	//method
	@Override
	public IContainer<IFlatTableDto> loadFlatTables() {
		return
		databaseNodeSearcher
		.getOriTableNodesFromDatabaseNode(databaseNode)
		.to(flatTableDtoMapper::createFlatTableDTOFromTableNode);
	}
	
	//method
	@Override
	public ITableDto loadTableById(final String id) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableIdFromDatabaseNode(databaseNode, id);
		
		return loadTableFromTableNode(tableNode);
	}
	
	//method
	@Override
	public ITableDto loadTableByName(final String name) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(databaseNode, name);
		
		return loadTableFromTableNode(tableNode);
	}
	
	//method
	@Override
	public IContainer<ITableDto> loadTables() {
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
	private IContainer<IColumnDto> loadColumnsFromTableNode(final IMutableNode<?> tableNode) {
		return
		tableNodeSearcher.getOriColumnNodesFromTableNode(tableNode).to(columnDtoMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	private ITableDto loadTableFromTableNode(final IMutableNode<?> tableNode) {
		return
		new TableDto(
			tableNodeSearcher.getOriIdNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
			tableNodeSearcher.getOriNameNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
			new SaveStampConfigurationDto(SaveStampStrategy.OWN_SAVE_STAMP),
			loadColumnsFromTableNode(tableNode)
		);
	}
}
