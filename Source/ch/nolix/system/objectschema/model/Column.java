package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.datamodel.id.IdCreator;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectschema.modeltool.ColumnTool;
import ch.nolix.system.objectschema.modelvalidator.ColumnValidator;
import ch.nolix.systemapi.midschema.adapter.ISchemaAdapter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeleditor.IColumnEditor;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;
import ch.nolix.systemapi.objectschema.modelvalidator.IColumnValidator;

public final class Column extends AbstractSchemaObject implements IColumn {
  private static final String INITIAL_HEADER = StringCatalog.DEFAULT_STRING;

  private static final IColumnEditor<Column> COLUMN_EDITOR = new ColumnEditor();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private static final IColumnValidator COLUMN_VALIDATOR = new ColumnValidator();

  private final String id;

  private Table parentTable;

  private String name = INITIAL_HEADER;

  private FieldType fieldType = FieldType.VALUE_FIELD;

  private DataType dataType = DataType.STRING;

  private final IArrayList<ITable> referenceableTables = ArrayList.createEmpty();

  private final IArrayList<IColumn> backReferenceableColumns = ArrayList.createEmpty();

  public Column(
    final String name,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    this(
      IdCreator.createIdOf10HexadecimalCharacters(),
      name,
      fieldType,
      dataType,
      referenceableTables,
      backReferenceableColumns);
  }

  private Column(
    final String id,
    final String name,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id = id;
    setName(name);
    this.referenceableTables.addAtEnd(referenceableTables);

    setContentModel(fieldType, dataType, referenceableTables, backReferenceableColumns);
  }

  public static Column withIdAndNameAndContentModel(
    final String id,
    final String name,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    return new Column(id, name, fieldType, dataType, referenceableTables, backReferenceableColumns);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public boolean belongsToDatabase() {
    return //
    parentTable != null
    && parentTable.belongsToDatabase();
  }

  @Override
  public boolean belongsToTable() {
    return (parentTable != null);
  }

  @Override
  public void delete() {
    COLUMN_EDITOR.deleteColumn(this);
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public FieldType getFieldType() {
    return fieldType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public IContainer<? extends IColumn> getStoredBackReferenceableColumns() {
    return backReferenceableColumns;
  }

  @Override
  public IDatabase getStoredParentDatabase() {
    return getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public Table getStoredParentTable() {
    COLUMN_TOOL.assertBelongsToTable(this);

    return parentTable;
  }

  @Override
  public IContainer<? extends ITable> getStoredReferenceableTables() {
    return referenceableTables;
  }

  @Override
  public boolean isBackReferenced() {
    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return false;
    }

    return isBackReferencedWhenIsAnyReferenceColumn();
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (belongsToTable() && getStoredParentTable().isConnectedWithRealDatabase());
  }

  @Override
  public boolean isEmpty() {
    return //
    isNew()
    || getStoredMidSchemaAdapter().columnIsEmpty(getStoredParentTable().getName(), getName());
  }

  @Override
  public boolean referencesBackColumn(final IColumn column) {
    return getStoredBackReferenceableColumns().contains(column);
  }

  @Override
  public boolean referencesTable(final ITable table) {
    return referenceableTables.contains(table);
  }

  @Override
  public Column setContentModel(
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    COLUMN_EDITOR.setContentModelToColumn(this, fieldType, dataType, referenceableTables, backReferenceableColumns);

    return this;
  }

  @Override
  public Column setName(final String name) {
    COLUMN_EDITOR.setNameToColumn(this, name);

    return this;
  }

  IContainer<? extends IColumn> getStoredBackReferencingColumns() {
    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return LinkedList.createEmpty();
    }

    return getStoredBackReferencingColumnsWhenIsReferenceColumn();
  }

  ISchemaAdapter getStoredMidSchemaAdapter() {
    return ((Database) COLUMN_TOOL.getParentDatabase(this)).getStoredMidSchemaAdapter();
  }

  void internalSetContentModelToDatabase() {
    final var table = getStoredParentTable();
    final var tableId = table.getId();
    final var tableName = table.getName();
    final var tableIdentification = new TableIdentification(tableId, tableName);
    final var columnId = getId();
    final var columnName = getName();
    final var columnIdentification = new ColumnIdentification(columnId, columnName);
    final var localFieldType = getFieldType();
    final var localDataType = getDataType();
    final var referenceableTableIdsView = getStoredReferenceableTables().getViewOf(ITable::getId);
    final var backReferenceableColumnIdsView = getStoredBackReferenceableColumns().getViewOf(IColumn::getId);

    getStoredMidSchemaAdapter().setColumnModel(
      tableIdentification,
      columnIdentification,
      localFieldType,
      localDataType,
      referenceableTableIdsView,
      backReferenceableColumnIdsView);
  }

  void setNameAttribute(final String header) {
    this.name = header;
  }

  void setContentModelAttribute(
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    COLUMN_VALIDATOR.assertCanSetContentModel(this, fieldType, dataType, referenceableTables, backReferenceableColumns);

    this.referenceableTables.clear();
    this.backReferenceableColumns.clear();

    this.fieldType = fieldType;
    this.dataType = dataType;
    this.referenceableTables.addAtEnd(referenceableTables);
    this.backReferenceableColumns.addAtEnd(backReferenceableColumns);
  }

  void setParentTableAttribute(final Table parentTable) {
    this.parentTable = parentTable;
  }

  @Override
  protected void noteClose() {
    //Does nothing.
  }

  private IContainer<? extends IColumn> getStoredBackReferencingColumnsWhenIsReferenceColumn() {
    if (COLUMN_TOOL.belongsToDatabase(this)) {
      return COLUMN_TOOL
        .getParentDatabase(this)
        .getStoredTables()
        .toMultiples(t -> t.getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this)));
    }

    if (belongsToTable()) {
      return //
      getStoredParentTable().getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this));
    }

    return LinkedList.createEmpty();
  }

  private boolean isBackReferencedWhenIsAnyReferenceColumn() {
    if (COLUMN_TOOL.belongsToDatabase(this)) {
      return COLUMN_TOOL
        .getParentDatabase(this)
        .getStoredTables()
        .containsAny(t -> t.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this)));
    }

    if (belongsToTable()) {
      return getStoredParentTable().getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this));
    }

    return false;
  }
}
