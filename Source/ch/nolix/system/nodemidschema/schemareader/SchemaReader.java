package ch.nolix.system.nodemidschema.schemareader;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.nodemidschema.flatmodelmapper.FlatTableDtoMapper;
import ch.nolix.system.nodemidschema.modelmapper.ColumnDtoMapper;
import ch.nolix.system.nodemidschema.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.nodemidschemaapi.flatmodelmapperapi.IFlatTableDtoMapper;
import ch.nolix.systemapi.nodemidschemaapi.modelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.nodemidschemaapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.ITableNodeSearcher;

public final class SchemaReader implements ISchemaReader {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final IFlatTableDtoMapper FLAT_TABLE_DTO_MAPPER = new FlatTableDtoMapper();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final IMutableNode<?> nodeDatabase;

  private SchemaReader(final IMutableNode<?> nodeDatabase) {

    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public static SchemaReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new SchemaReader(nodeDatabase);
  }

  @Override
  public boolean columnIsEmpty(String tableName, String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_EXAMINER.columnOfTableNodeIsEmptyByColumnName(tableNode, columnName);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getTableCount() {
    return DATABASE_NODE_SEARCHER.getTableNodeCount(nodeDatabase);
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, tableId);
    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    return columnNodes.to(COLUMN_DTO_MAPPER::mapColumnNodeToColumnDto);
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    return columnNodes.to(COLUMN_DTO_MAPPER::mapColumnNodeToColumnDto);
  }

  @Override
  public FlatTableDto loadFlatTableById(final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, id);

    return FLAT_TABLE_DTO_MAPPER.mapTableNodeToFlatTableDto(tableNode);
  }

  @Override
  public FlatTableDto loadFlatTableByName(final String name) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, name);

    return FLAT_TABLE_DTO_MAPPER.mapTableNodeToFlatTableDto(tableNode);
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);

    return tableNodes.to(FLAT_TABLE_DTO_MAPPER::mapTableNodeToFlatTableDto);
  }

  @Override
  public Time loadSchemaTimestamp() {

    final var databasePropertiesNode = //
    DATABASE_NODE_SEARCHER.getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var timestampNode = //
    DATABASE_PROPERTIES_NODE_SEARCHER.getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    return Time.fromSpecification(timestampNode);
  }

  @Override
  public TableDto loadTableById(final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, id);

    return loadTableFromTableNode(tableNode);
  }

  @Override
  public TableDto loadTableByName(final String name) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, name);

    return loadTableFromTableNode(tableNode);
  }

  @Override
  public IContainer<TableDto> loadTables() {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);

    return tableNodes.to(this::loadTableFromTableNode);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  private IContainer<ColumnDto> loadColumnsFromTableNode(final IMutableNode<?> tableNode) {

    final var columnNodes = TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);

    return columnNodes.to(COLUMN_DTO_MAPPER::mapColumnNodeToColumnDto);
  }

  private TableDto loadTableFromTableNode(final IMutableNode<?> tableNode) {

    final var tableId = TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
    final var tableName = TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
    final var columns = loadColumnsFromTableNode(tableNode);

    return new TableDto(tableId, tableName, columns);
  }
}
