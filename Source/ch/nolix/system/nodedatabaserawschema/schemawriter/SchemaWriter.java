//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class SchemaWriter implements ISchemaWriter {
	
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
	private static final TableNodeMapper tableNodeMapper = new TableNodeMapper();
	
	//static attribute
	private static final ColumnNodeMapper columnNodeMapper = new ColumnNodeMapper();
	
	//static attribute
	private static final ParametrizedPropertyTypeNodeMapper parametrizedPropertyTypeNodeMapper =
	new ParametrizedPropertyTypeNodeMapper();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private int saveCount;
	
	//attribute
	private final BaseNode databaseNode;
	
	//attribute
	private BaseNode editedDatabaseNode;
	
	//attribute
	private boolean hasChanges;
	
	//constructor
	public SchemaWriter(final BaseNode databaseNode) {
		
		Validator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
		
		reset();
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDTO column) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		
		tableNode.addAttribute(columnNodeMapper.createColumnNodeFrom(column));
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		
		editedDatabaseNode.addAttribute(tableNodeMapper.createTableNodeFrom(table));
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnName) {
		
		final var tableNode = databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		tableNode.removeFirstAttribute(
			a -> 
			a.hasHeader(SubNodeHeaderCatalogue.COLUMN)
			&& columnNodeSearcher.getRefNameNodeFromColumnNode(a).getRefOneAttribute().hasHeader(columnName)
		);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		
		editedDatabaseNode.removeFirstAttribute(
			a -> 
			a.hasHeader(SubNodeHeaderCatalogue.TABLE)
			&&  tableNodeSearcher.getRefNameNodeFromTableNode(a).getRefOneAttribute().hasHeader(tableName)
		);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getSaveCount() {
		return saveCount;
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return hasChanges;
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public void reset() {
		
		editedDatabaseNode = databaseNode.getCopy();
		
		hasChanges = false;
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		try {
			
			databaseNode.resetAttributes(editedDatabaseNode.getRefAttributes());
			
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	@Override
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		
		final var columnNode = tableNodeSearcher.getRefColumnNodeFromTableNodeByColumnName(tableNode, columnName);
		final var headerNode = columnNodeSearcher.getRefNameNodeFromColumnNode(columnNode);
		headerNode.setHeader(newColumnName);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String columnId,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		
		final var columnNode = databaseNodeSearcher.getRefColumnNodeByColumnIdFromDatabaseNode(databaseNode, columnId);
		
		columnNode.replaceFirstAttributeHavingGivenHeaderWithGivenAttribute(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
			parametrizedPropertyTypeNodeMapper.createParametrizedPropertyTypeNodeFrom(parametrizedPropertyType)
		);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getRefDatabasePropertiesNodeFromDatabaseNode(editedDatabaseNode);
		
		final var schemaTimestampNode =
		databasePropertiesNodeSearcher.getSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		schemaTimestampNode.getRefOneAttribute().setHeader(schemaTimestamp.getSpecification().getOneAttributeHeader());
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		
		final var tableNode =
		databaseNodeSearcher.getRefTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		
		final var nameNode = tableNodeSearcher.getRefNameNodeFromTableNode(tableNode);
		nameNode.getRefOneAttribute().setHeader(newTableName);
		
		hasChanges = true;
	}
}
