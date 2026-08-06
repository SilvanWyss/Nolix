/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.loader;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.midschemainfo.modelsearcher.DatabaseInfoSearcher;
import ch.nolix.systemapi.middata.loader.IDataReader;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class DataReader implements IDataReader {
  private static final DatabaseInfoSearcher DATABASE_VIEW_SEARCHER = new DatabaseInfoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseInfoDto databaseView;

  private final InternalDataReader internalDataReader;

  private DataReader(final IMutableNode<?> nodeDatabase, final DatabaseInfoDto databaseView) {
    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
    this.internalDataReader = InternalDataReader.forNodeDatabase(nodeDatabase);
  }

  public static DataReader forNodeDatabaseAndDatabaseView(
    final IMutableNode<?> nodeDatabase,
    final DatabaseInfoDto databaseView) {
    return new DataReader(nodeDatabase, databaseView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDatabaseName() {
    return internalDataReader.getDatabaseName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getEntityCount(final String tableName) {
    return internalDataReader.getEntityCount(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITime getSchemaTimestamp() {
    return internalDataReader.getSchemaTimestamp();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<String> loadMultiBackReferenceBackReferencedEntityIds(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {
    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    return //
    internalDataReader.loadMultiBackReferenceBackReferencedEntityIds(tableName, entityId, multiBackReferenceColumnView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<MultiBackReferenceEntryDto> loadMultiBackReferenceEntries(
    final TableIdentification table,
    final String entityId,
    final ColumnIdentification multiBackReferenceColumn) {
    final var tableName = table.tableName();
    final var multiBackReferenceColumnName = multiBackReferenceColumn.columnName();

    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    return internalDataReader.loadMultiBackReferenceEntries(tableName, entityId, multiBackReferenceColumnView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);

    return internalDataReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Object> loadMultiValueValues(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    final var columnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);

    return internalDataReader.loadMultiValueEntries(tableName, entityId, columnView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<EntityLoadingDto> loadEntities(final String tableName) {
    final var tableView = getTableViewByTableName(tableName);

    return internalDataReader.loadEntitiesOfTable(tableView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityLoadingDto loadEntity(final String tableName, final String entityId) {
    final var tableView = getTableViewByTableName(tableName);

    return internalDataReader.loadEntity(tableView, entityId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableContainsEntityWithValueAtColumn(
    final String tableName,
    final String columnName,
    final String value) {
    final var columnView = getColumnViewByTableNameAndColumnName(tableName, columnName);

    return internalDataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnView, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    final String tableName,
    final String columnName,
    final String value,
    final ExtendedIterable<String> entitiesToIgnoreIds) {
    final var columnView = getColumnViewByTableNameAndColumnName(tableName, columnName);

    return //
    internalDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnView,
      value,
      entitiesToIgnoreIds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableContainsEntity(final String tableName, final String entityId) {
    return internalDataReader.tableContainsEntityWithGivenId(tableName, entityId);
  }

  private ColumnInfoDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnName(databaseView, tableName, columnName);
  }

  private TableInfoDto getTableViewByTableName(final String tableName) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableName(databaseView, tableName);
  }
}
