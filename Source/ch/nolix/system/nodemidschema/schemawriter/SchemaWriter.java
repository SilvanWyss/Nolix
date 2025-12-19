package ch.nolix.system.nodemidschema.schemawriter;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.time.moment.IIncrementalCurrentTimeCreator;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class SchemaWriter implements ISchemaWriter {
  private static final IIncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
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

  @Override
  public void addColumn(final TableIdentification table, final ColumnDto column) {
    SchemaWriterActionProvider.addColumn(editedNodeDatabase, table, column);
    hasChanges = true;
  }

  @Override
  public void addTable(final TableDto table) {
    SchemaWriterActionProvider.addTable(editedNodeDatabase, table);
    hasChanges = true;
  }

  @Override
  public void deleteColumn(final TableIdentification table, final String columnName) {
    SchemaWriterActionProvider.deleteColumn(editedNodeDatabase, table, columnName);
    hasChanges = true;
  }

  @Override
  public void deleteTable(final String tableName) {
    SchemaWriterActionProvider.deleteTable(editedNodeDatabase, tableName);
    hasChanges = true;
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return saveCount;
  }

  @Override
  public boolean hasChanges() {
    return hasChanges;
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    SchemaWriterActionProvider.renameColumn(editedNodeDatabase, tableName, columnName, newColumnName);
    hasChanges = true;
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {
    SchemaWriterActionProvider.renameTable(editedNodeDatabase, tableName, newTableName);
    hasChanges = true;
  }

  @Override
  public void reset() {
    editedNodeDatabase = MutableNode.fromNode(nodeDatabase);

    hasChanges = false;
  }

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

  @Override
  public void setColumnModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<String> referenceableTableIds,
    final IContainer<String> backReferenceableColumnIds) {
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
