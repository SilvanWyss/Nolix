package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.NodeDatabaseSearcher;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.objectschema.schemadto.TableDto;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

public final class SchemaReader implements ISchemaReader {

  private static final NodeDatabaseSearcher DATABASE_NODE_SEARCHER = new NodeDatabaseSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final FlatTableDtoMapper FLAT_TABLE_DTO_MAPPER = new FlatTableDtoMapper();

  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final CloseController closeController = CloseController.forElement(this);

  private final IMutableNode<?> nodeDatabase;

  private SchemaReader(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("database Node").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public static SchemaReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new SchemaReader(nodeDatabase);
  }

  @Override
  public boolean columnIsEmpty(String tableName, String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);

    return COLUMN_NODE_SEARCHER.columnNodeContainsEntityNode(columnNode);
  }

  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getTableCount() {
    return DATABASE_NODE_SEARCHER.getTableNodeCount(nodeDatabase);
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, tableId);

    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
      .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode)
      .to(COLUMN_DTO_MAPPER::createColumnDtoFromColumnNode);
  }

  @Override
  public IFlatTableDto loadFlatTableById(final String id) {
    return FLAT_TABLE_DTO_MAPPER.createFlatTableDtoFromTableNode(
      DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, id));
  }

  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return FLAT_TABLE_DTO_MAPPER.createFlatTableDtoFromTableNode(
      DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, name));
  }

  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return DATABASE_NODE_SEARCHER
      .getStoredTableNodesFromNodeDatabase(nodeDatabase)
      .to(FLAT_TABLE_DTO_MAPPER::createFlatTableDtoFromTableNode);
  }

  @Override
  public ITableDto loadTableById(final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, id);

    return loadTableFromTableNode(tableNode);
  }

  @Override
  public ITableDto loadTableByName(final String name) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, name);

    return loadTableFromTableNode(tableNode);
  }

  @Override
  public IContainer<ITableDto> loadTables() {
    return DATABASE_NODE_SEARCHER
      .getStoredTableNodesFromNodeDatabase(nodeDatabase)
      .to(this::loadTableFromTableNode);
  }

  @Override
  public Time loadSchemaTimestamp() {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var timestampNode = DATABASE_PROPERTIES_NODE_SEARCHER
      .getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(timestampNode);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  private IContainer<ColumnDto> loadColumnsFromTableNode(final IMutableNode<?> tableNode) {
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
