//package declaration
package ch.nolix.system.noderawobjectschema.schemareader;

//class
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.noderawobjectschema.structure.ColumnNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.TableNodeSearcher;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.SaveStampStrategy;

//class
public final class SchemaReader implements ISchemaReader {
	
	//static attributes
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	private static final DatabasePropertiesNodeSearcher databasePropertiesNodeSearcher =
	new DatabasePropertiesNodeSearcher();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//static attributes
	private static final FlatTableDTOMapper flatTableDTOMapper = new FlatTableDTOMapper();
	private static final ColumnDTOMapper columnDTOMapper = new ColumnDTOMapper();
	
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
		
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(databaseNode, tableName);
		
		final var columnNode = tableNodeSearcher.getRefColumnNodeFromTableNodeByColumnName(tableNode, columnName);
		
		return columnNodeSearcher.columnNodeContainsEntityNode(columnNode);
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(String tableName) {
		
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(databaseNode, tableName);
		
		return
		tableNodeSearcher.getRefColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return
		databaseNodeSearcher
		.getTableNodesFromDatabaseNode(databaseNode)
		.to(flatTableDTOMapper::createFlatTableDTOFromTableNode);
	}
	
	//method
	@Override
	public ITableDTO loadTable(final String tableName) {
		return
		new TableDTO(
			"Id", //TODO: Complete.
			tableName,
			new SaveStampConfigurationDTO(SaveStampStrategy.OWN_SAVE_STAMP),
			loadColumns(tableName)
		);
	}
	
	//method
	@Override
	public LinkedList<ITableDTO> loadTables() {
		return loadFlatTables().to(t -> loadTable(t.getName()));
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		final var timestampNode =
		databasePropertiesNodeSearcher.getSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(timestampNode);
	}
}
