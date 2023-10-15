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

  // constant
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  // constant
  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
      new DatabasePropertiesNodeSearcher();

  // constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  // constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  // constant
  private static final FlatTableDtoMapper FLAT_TABLE_DTO_MAPPER = new FlatTableDtoMapper();

  // constant
  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  // attribute
  private final CloseController closeController = CloseController.forElement(this);

  // attribute
  private final IMutableNode<?> databaseNode;

  // constructor
  public SchemaReader(final IMutableNode<?> databaseNode) {

    GlobalValidator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();

    this.databaseNode = databaseNode;
  }

  // method
  @Override
  public boolean columnIsEmpty(String tableName, String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);

    return COLUMN_NODE_SEARCHER.columnNodeContainsEntityNode(columnNode);
  }

  // method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  // method
  @Override
  public int getTableCount() {
    return DATABASE_NODE_SEARCHER.getTableNodeCount(databaseNode);
  }

  // method
  @Override
  public IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode, tableId);

    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
        .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  // method
  @Override
  public IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);

    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
        .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  // method
  @Override
  public IFlatTableDto loadFlatTableById(final String id) {
    return FLAT_TABLE_DTO_MAPPER.createFlatTableDtoFromTableNode(
        DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode, id));
  }

  // method
  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return FLAT_TABLE_DTO_MAPPER.createFlatTableDtoFromTableNode(
        DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, name));
  }

  // method
  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return DATABASE_NODE_SEARCHER
        .getStoredTableNodesFromDatabaseNode(databaseNode)
        .to(FLAT_TABLE_DTO_MAPPER::createFlatTableDtoFromTableNode);
  }

  // method
  @Override
  public ITableDto loadTableById(final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode, id);

    return loadTableFromTableNode(tableNode);
  }

  // method
  @Override
  public ITableDto loadTableByName(final String name) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, name);

    return loadTableFromTableNode(tableNode);
  }

  // method
  @Override
  public IContainer<ITableDto> loadTables() {
    return DATABASE_NODE_SEARCHER
        .getStoredTableNodesFromDatabaseNode(databaseNode)
        .to(this::loadTableFromTableNode);
  }

  // method
  @Override
  public Time loadSchemaTimestamp() {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
        .getStoredDatabasePropertiesNodeFromDatabaseNode(databaseNode);

    final var timestampNode = DATABASE_PROPERTIES_NODE_SEARCHER
        .getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(timestampNode);
  }

  // method
  @Override
  public void noteClose() {
    // Does nothing.
  }

  // method
  private IContainer<IColumnDto> loadColumnsFromTableNode(final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
        .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  // method
  private ITableDto loadTableFromTableNode(final IMutableNode<?> tableNode) {
    return new TableDto(
        TABLE_NODE_SEARCHER.getStoredIdNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
        TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
        new SaveStampConfigurationDto(SaveStampStrategy.OWN_SAVE_STAMP),
        loadColumnsFromTableNode(tableNode));
  }
}
