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
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class SchemaWriter implements ISchemaWriter {

  //constant
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  //constant
  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  //constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  //constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  //constant
  private static final TableNodeMapper TABLE_NODE_MAPPER = new TableNodeMapper();

  //constant
  private static final ColumnNodeMapper columnNodeMapper = new ColumnNodeMapper();

  //constant
  private static final ParameterizedPropertyTypeNodeMapper parameterizedPropertyTypeNodeMapper = //
  new ParameterizedPropertyTypeNodeMapper();

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

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    tableNode.addChildNode(columnNodeMapper.createColumnNodeFrom(column));

    hasChanges = true;
  }

  //method
  @Override
  public void addTable(final ITableDto table) {

    editedDatabaseNode.addChildNode(TABLE_NODE_MAPPER.createTableNodeFrom(table));

    hasChanges = true;
  }

  //method
  @Override
  public void deleteColumn(final String tableName, final String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    tableNode.removeFirstChildNodeThat(
      (final IMutableNode<?> a) -> a.hasHeader(SubNodeHeaderCatalogue.COLUMN)
      && COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(a).getStoredSingleChildNode()
        .hasHeader(columnName));

    hasChanges = true;
  }

  //method
  @Override
  public void deleteTable(final String tableName) {

    editedDatabaseNode.removeFirstChildNodeThat(
      (final IMutableNode<?> a) -> a.hasHeader(SubNodeHeaderCatalogue.TABLE)
      && TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(a).getStoredSingleChildNode().hasHeader(tableName));

    hasChanges = true;
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
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
  public void saveChanges() {
    try {

      setSchemaTimestamp(Time.ofNow());
      databaseNode.setChildNodes(editedDatabaseNode.getStoredChildNodes());

      saveCount++;
    } finally {
      reset();
    }
  }

  //method
  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);
    final var headerNode = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode);
    headerNode.setHeader(newColumnName);

    hasChanges = true;
  }

  //method
  @Override
  public void setColumnParameterizedPropertyType(
    final String columnId,
    final IParameterizedPropertyTypeDto parameterizedPropertyType) {

    final var columnNode = DATABASE_NODE_SEARCHER.getStoredColumnNodeByColumnIdFromDatabaseNode(databaseNode, columnId);

    columnNode.replaceFirstChildNodeWithGivenHeaderByGivenNode(
      SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      parameterizedPropertyTypeNodeMapper.createParameterizedPropertyTypeNodeFrom(parameterizedPropertyType));

    hasChanges = true;
  }

  //method
  @Override
  public void setTableName(final String tableName, final String newTableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    final var nameNode = TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode);
    nameNode.getStoredSingleChildNode().setHeader(newTableName);

    hasChanges = true;
  }

  //method
  private void setSchemaTimestamp(final ITime schemaTimestamp) {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromDatabaseNode(editedDatabaseNode);

    final var schemaTimestampNode = DATABASE_PROPERTIES_NODE_SEARCHER
      .getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    schemaTimestampNode
      .getStoredSingleChildNode()
      .setHeader(schemaTimestamp.getSpecification().getSingleChildNodeHeader());

    hasChanges = true;
  }
}
