package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class Column implements IColumn {
  private static final String DATA_TYPE_CLASS_VARIABLE = "data type class";

  private final Table<IEntity> parentTable;

  private final String id;

  private final String name;

  private final FieldType fieldType;

  private final Class<?> dataTypeClass;

  private final ImmutableList<? extends ITable<IEntity>> referenceableTables;

  private final ImmutableList<? extends IColumn> backReferenceableColumns;

  private Column(
    final Table<IEntity> parentTable,
    final String id, final String name,
    final FieldType fieldType,
    final Class<?> dataTypeClass,
    final IContainer<? extends ITable<IEntity>> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(fieldType).thatIsNamed(FieldType.class).isNotNull();
    Validator.assertThat(dataTypeClass).thatIsNamed(DATA_TYPE_CLASS_VARIABLE).isNotNull();

    this.parentTable = parentTable;
    this.id = id;
    this.name = name;
    this.fieldType = fieldType;
    this.dataTypeClass = dataTypeClass;
    this.referenceableTables = ImmutableList.fromIterable(referenceableTables);
    this.backReferenceableColumns = ImmutableList.fromIterable(backReferenceableColumns);
  }

  public static Column //
  withParentTableAndIdAndNameAndFieldTypeAndDataTypeClassAndReferenceableTablesAndBackReferenceableColumns(
    final Table<IEntity> parentTable,
    final String id,
    final String name,
    final FieldType fieldType,
    final Class<?> dataTypeClass,
    final IContainer<? extends ITable<IEntity>> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    return new Column(parentTable, id, name, fieldType, dataTypeClass, referenceableTables, backReferenceableColumns);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsValueInPersistedData(final String value) {
    final var midDataAdapterAndSchemaReader = parentTable.getStoredMidDataDataAdapterAndSchemaReader();
    final var tableName = parentTable.getName();
    final var columnName = getName();

    return midDataAdapterAndSchemaReader.tableContainsEntityWithValueAtColumn(tableName, columnName, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsValueInPersistedDataIgnoringEntities(
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    final var midDataAdapterAndSchemaReader = parentTable.getStoredMidDataDataAdapterAndSchemaReader();
    final var tableName = parentTable.getName();
    final var columnName = getName();

    return //
    midDataAdapterAndSchemaReader.tableContainsEntityWithValueAtColumnIgnoringEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<?> getDataTypeClass() {
    return dataTypeClass;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldType getFieldType() {
    return fieldType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseObjectState getState() {
    if (getStoredParentTable().isClosed()) {
      return DatabaseObjectState.CLOSED;
    }
    return DatabaseObjectState.UNEDITED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IColumn> getStoredBackReferenceableColumns() {
    return backReferenceableColumns;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITable<IEntity> getStoredParentTable() {
    return parentTable;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends ITable<IEntity>> getStoredReferenceableTables() {
    return referenceableTables;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isClosed() {
    return getStoredParentTable().isClosed();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isConnectedWithRealDatabase() {
    return getStoredParentTable().isConnectedWithRealDatabase();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleted() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEdited() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLoaded() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNew() {
    return false;
  }
}
