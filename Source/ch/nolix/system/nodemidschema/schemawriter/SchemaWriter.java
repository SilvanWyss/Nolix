/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.schemawriter;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.time.timetool.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class SchemaWriter implements ISchemaWriter {
  private static final IncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
  new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private final IMutableNode<?> nodeDatabase;

  private IMutableNode<?> editedNodeDatabase;

  private boolean hasChanges;

  private SchemaWriter(final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(nodeDatabase).thatIsNamed("database Node").isNotNull();

    this.nodeDatabase = nodeDatabase;

    reset();
  }

  public static SchemaWriter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new SchemaWriter(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addColumn(final TableIdentification table, final ColumnDto column) {
    SchemaWriterActionProvider.addColumn(editedNodeDatabase, table, column);
    hasChanges = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addTable(final TableDto table) {
    SchemaWriterActionProvider.addTable(editedNodeDatabase, table);
    hasChanges = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumn(final TableIdentification table, final String columnName) {
    SchemaWriterActionProvider.deleteColumn(editedNodeDatabase, table, columnName);
    hasChanges = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteTable(final String tableName) {
    SchemaWriterActionProvider.deleteTable(editedNodeDatabase, tableName);
    hasChanges = true;
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
  public int getSaveCount() {
    return saveCount;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChanges() {
    return hasChanges;
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
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    SchemaWriterActionProvider.renameColumn(editedNodeDatabase, tableName, columnName, newColumnName);
    hasChanges = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void renameTable(final String tableName, final String newTableName) {
    SchemaWriterActionProvider.renameTable(editedNodeDatabase, tableName, newTableName);
    hasChanges = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    editedNodeDatabase = MutableNode.fromNode(nodeDatabase);

    hasChanges = false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveChanges() {
    try {
      setSchemaTimestamp(INCREMENTAL_CURRENT_TIME_CREATOR.getCurrentTime());
      nodeDatabase.setChildNodes(editedNodeDatabase.getStoredChildNodes());

      saveCount++;
    } finally {
      reset();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setColumnModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final ExtendedIterable<String> referenceableTableIds,
    final ExtendedIterable<String> backReferenceableColumnIds) {
    SchemaWriterActionProvider.setColumnModel(
      editedNodeDatabase,
      table,
      column,
      fieldType,
      dataType,
      referenceableTableIds,
      backReferenceableColumnIds);

    hasChanges = true;
  }

  private void setSchemaTimestamp(final ITime schemaTimestamp) {
    SchemaWriterActionProvider.setSchemaTimestamp(editedNodeDatabase, schemaTimestamp);
    hasChanges = true;
  }
}
