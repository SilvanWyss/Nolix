//package declaration
package ch.nolix.system.noderawobjectschema.schemawriter;

import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.noderawobjectschema.structure.ColumnNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawobjectschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.noderawobjectschema.structure.TableNodeSearcher;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class SchemaWriter implements ISchemaWriter {
	
	//static attributes
	private static final DatabaseNodeSearcher databaseNodeSearcher = new DatabaseNodeSearcher();
	private static final DatabasePropertiesNodeSearcher databasePropertiesNodeSearcher =
	new DatabasePropertiesNodeSearcher();
	private static final TableNodeSearcher tableNodeSearcher = new TableNodeSearcher();
	private static final ColumnNodeSearcher columnNodeSearcher = new ColumnNodeSearcher();
	
	//static attributes
	private static final TableNodeMapper tableNodeMapper = new TableNodeMapper();
	private static final ColumnNodeMapper columnNodeMapper = new ColumnNodeMapper();
	private static final ParametrizedPropertyTypeNodeMapper parametrizedPropertyTypeNodeMapper =
	new ParametrizedPropertyTypeNodeMapper();
	
	//attributes
	private final BaseNode databaseNode;
	private final BaseNode editedDatabaseNode;
	private boolean hasChanges;
	
	//constructor
	public SchemaWriter(final BaseNode databaseNode) {
		
		Validator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
		editedDatabaseNode = databaseNode.getCopy();
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
	public boolean hasChanges() {
		return hasChanges;
	}
	
	//method
	@Override
	public void saveChanges() {
		if (hasChanges()) {
			saveChangesWhenHasChanges();
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
		
		//TODO: create BaseNode::setAttribute method.
		
		columnNode.removeFirstAttribute(SubNodeHeaderCatalogue.PARAMETRIZED_PROPERTY_TYPE);
		
		columnNode.addAttribute(
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
	
	//method
	private void saveChangesWhenHasChanges() {
		
		databaseNode.resetAttributes(editedDatabaseNode.getRefAttributes());
		
		hasChanges = false;
	}
}
