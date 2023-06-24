//package declaration
package ch.nolix.system.nodedatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ColumnNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

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
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private int saveCount;
	
	//attribute
	private final IMutableNode<?> databaseNode;
	
	//attribute
	private IMutableNode<?> editedDatabaseNode;
	
	//attribute
	private boolean hasChanges;
	
	//constructor
	public SchemaWriter(final IMutableNode<?> databaseNode) {
		
		GlobalValidator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
		
		reset();
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDto column) {
		
		final var tableNode =
		databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		
		tableNode.addChildNode(columnNodeMapper.createColumnNodeFrom(column));
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void addTable(final ITableDto table) {
		
		editedDatabaseNode.addChildNode(tableNodeMapper.createTableNodeFrom(table));
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnName) {
		
		final var tableNode = databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		tableNode.removeFirstChildNodeThat(
			(final IMutableNode<?> a) ->
			a.hasHeader(SubNodeHeaderCatalogue.COLUMN)
			&& columnNodeSearcher.getOriNameNodeFromColumnNode(a).getOriSingleChildNode().hasHeader(columnName)
		);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		
		editedDatabaseNode.removeFirstChildNodeThat(
			(final IMutableNode<?> a) ->
			a.hasHeader(SubNodeHeaderCatalogue.TABLE)
			&&  tableNodeSearcher.getOriNameNodeFromTableNode(a).getOriSingleChildNode().hasHeader(tableName)
		);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public CloseController getOriCloseController() {
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
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public void reset() {
		
		editedDatabaseNode = MutableNode.fromNode(databaseNode);
		
		hasChanges = false;
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		try {
			
			databaseNode.setChildNodes(editedDatabaseNode.getOriChildNodes());
			
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	@Override
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		
		final var tableNode =
		databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		
		final var columnNode = tableNodeSearcher.getOriColumnNodeFromTableNodeByColumnName(tableNode, columnName);
		final var headerNode = columnNodeSearcher.getOriNameNodeFromColumnNode(columnNode);
		headerNode.setHeader(newColumnName);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String columnId,
		final IParametrizedPropertyTypeDto parametrizedPropertyType
	) {
		
		final var columnNode = databaseNodeSearcher.getOriColumnNodeByColumnIdFromDatabaseNode(databaseNode, columnId);
		
		columnNode.replaceFirstChildNodeWithGivenHeaderByGivenNode(
			SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE,
			parametrizedPropertyTypeNodeMapper.createParametrizedPropertyTypeNodeFrom(parametrizedPropertyType)
		);
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final ITime schemaTimestamp) {
		
		final var databasePropertiesNode =
		databaseNodeSearcher.getOriDatabasePropertiesNodeFromDatabaseNode(editedDatabaseNode);
		
		final var schemaTimestampNode =
		databasePropertiesNodeSearcher.getOriSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);
		
		schemaTimestampNode.getOriSingleChildNode().setHeader(schemaTimestamp.getSpecification().getSingleChildNodeHeader());
		
		hasChanges = true;
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		
		final var tableNode =
		databaseNodeSearcher.getOriTableNodeByTableNameFromDatabaseNode(editedDatabaseNode, tableName);
		
		final var nameNode = tableNodeSearcher.getOriNameNodeFromTableNode(tableNode);
		nameNode.getOriSingleChildNode().setHeader(newTableName);
		
		hasChanges = true;
	}
}
