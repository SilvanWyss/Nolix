package ch.nolix.system.objectdata.adapter;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.ICloseController;
import ch.nolix.system.objectdata.changesetsaver.ChangeSetSaver;
import ch.nolix.system.objectdata.model.Database;
import ch.nolix.system.objectdata.model.SchemaInitializer;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdataapi.adapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;

public abstract class AbstractDataAdapter implements IDataAdapter {

  private static final SchemaInitializer SCHEMA_INITIALIZER = new SchemaInitializer();

  private static final ChangeSetSaver DATA_SAVER = new ChangeSetSaver();

  private final String databaseName;

  private final IEntityTypeSet entityTypeSet;

  private final ICloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private Database database;

  private IDataAdapterAndSchemaReader midDataAdapterAndSchemaReader;

  protected AbstractDataAdapter(
    final String databaseName,
    final IEntityTypeSet entityTypeSet,
    final ISchemaAdapter schemaAdapter,
    final Supplier<IDataAdapterAndSchemaReader> midDataAdapterAndSchemaReader) {

    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();

    SCHEMA_INITIALIZER.initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(entityTypeSet, schemaAdapter);
    schemaAdapter.close();

    this.midDataAdapterAndSchemaReader = midDataAdapterAndSchemaReader.get();
    this.databaseName = databaseName;
    this.entityTypeSet = entityTypeSet;

    this.database = //
    Database.withEntityTypeSetAndMidDataAdapterAndSchemaReader(
      entityTypeSet,
      this.midDataAdapterAndSchemaReader.createEmptyCopy());

    createCloseDependencyTo(this.midDataAdapterAndSchemaReader);
  }

  @Override
  public final void createCloseDependencyTo(final GroupCloseable element) {
    IDataAdapter.super.createCloseDependencyTo(element);
  }

  @Override
  public final String getDatabaseName() {
    return databaseName;
  }

  @Override
  public final int getSaveCount() {
    return saveCount;
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final <E extends IEntity> ITable<E> getStoredTableByEntityType(
    final Class<E> entityType) {
    return database.getStoredTableByEntityType(entityType);
  }

  @Override
  public final boolean hasChanges() {
    return database.isEdited();
  }

  @Override
  public final <E extends IEntity> IDataAdapter insertEntity(
    final E entity,
    @SuppressWarnings("unchecked") final E... entities) {

    database.insertEntity(entity);

    for (final var e : entities) {
      database.insertEntity(e);
    }

    return this;
  }

  @Override
  public final void noteClose() {
    database.close();
  }

  @Override
  public final synchronized void reset() {

    database.close();

    midDataAdapterAndSchemaReader = midDataAdapterAndSchemaReader.createEmptyCopy();

    database = Database.withEntityTypeSetAndMidDataAdapterAndSchemaReader(entityTypeSet, midDataAdapterAndSchemaReader);
  }

  @Override
  public final synchronized void saveChanges() {
    try {
      saveChangesAndIncrementSaveCount();
    } finally {
      reset();
    }
  }

  protected final IEntityTypeSet getSchema() {
    return entityTypeSet;
  }

  private synchronized void saveChangesAndIncrementSaveCount() {

    DATA_SAVER.saveChangesOfDatabaseSynchronously(database, midDataAdapterAndSchemaReader);

    saveCount++;
  }
}
