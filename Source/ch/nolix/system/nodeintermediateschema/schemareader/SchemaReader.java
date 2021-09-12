//package declaration
package ch.nolix.system.nodeintermediateschema.schemareader;

//class
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.nodeintermediateschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodeintermediateschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodeintermediateschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodeintermediateschema.structure.TableNodeSearcher;
import ch.nolix.techapi.intermediateschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;

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
	public boolean columnIsEmpty(String tableName, String columnHeader) {
		
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(databaseNode, tableName);
		
		final var columnNode = tableNodeSearcher.getColumnNodeFromTableNode(tableNode, columnHeader);
		
		return columnNodeSearcher.columnNodeContainsEntityNode(columnNode);
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(String tableName) {
		
		final var tableNode = databaseNodeSearcher.getTableNodeFromDatabaseNode(databaseNode, tableName);
		
		return
		tableNodeSearcher.getColumnNodesFromTableNode(tableNode).to(columnDTOMapper::createColumnDTOFromColumnNode);
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
	public Time loadSchemaTimestamp() {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getDatabasePropertiesNodeFromDatabaseNode(databaseNode);
		
		final var timestampNode =
		databasePropertiesNodeSearcher.getSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		return Time.fromSpecification(timestampNode);
	}
}
