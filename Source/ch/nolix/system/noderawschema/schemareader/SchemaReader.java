package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.objectschema.schemadto.TableDto;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public final class SchemaReader implements ISchemaReader {

  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final FlatTableDtoMapper FLAT_TABLE_DTO_MAPPER = new FlatTableDtoMapper();

  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final CloseController closeController = CloseController.forElement(this);

  private final IMutableNode<?> databaseNode;

  private SchemaReader(final IMutableNode<?> databaseNode) {

    GlobalValidator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();

    this.databaseNode = databaseNode;
  }

  public static SchemaReader forDatabaseNode(final IMutableNode<?> databaseNode) {
    return new SchemaReader(databaseNode);
  }

  @Override
  public boolean columnIsEmpty(String tableName, String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);

    return COLUMN_NODE_SEARCHER.columnNodeContainsEntityNode(columnNode);
  }

  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getTableCount() {
    return DATABASE_NODE_SEARCHER.getTableNodeCount(databaseNode);
  }

  @Override
  public IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode, tableId);

    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
      .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  @Override
  public IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);

    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
      .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  @Override
  public IFlatTableDto loadFlatTableById(final String id) {
    return FLAT_TABLE_DTO_MAPPER.createFlatTableDtoFromTableNode(
      DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode, id));
  }

  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return FLAT_TABLE_DTO_MAPPER.createFlatTableDtoFromTableNode(
      DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, name));
  }

  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return DATABASE_NODE_SEARCHER
      .getStoredTableNodesFromDatabaseNode(databaseNode)
      .to(FLAT_TABLE_DTO_MAPPER::createFlatTableDtoFromTableNode);
  }

  @Override
  public ITableDto loadTableById(final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode, id);

    return loadTableFromTableNode(tableNode);
  }

  @Override
  public ITableDto loadTableByName(final String name) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, name);

    return loadTableFromTableNode(tableNode);
  }

  @Override
  public IContainer<ITableDto> loadTables() {
    return DATABASE_NODE_SEARCHER
      .getStoredTableNodesFromDatabaseNode(databaseNode)
      .to(this::loadTableFromTableNode);
  }

  @Override
  public Time loadSchemaTimestamp() {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromDatabaseNode(databaseNode);

    final var timestampNode = DATABASE_PROPERTIES_NODE_SEARCHER
      .getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(timestampNode);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  private IContainer<IColumnDto> loadColumnsFromTableNode(final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
      .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  private ITableDto loadTableFromTableNode(final IMutableNode<?> tableNode) {
    return new TableDto(
      TABLE_NODE_SEARCHER.getStoredIdNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
      TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode).getSingleChildNodeHeader(),
      loadColumnsFromTableNode(tableNode));
  }
}
