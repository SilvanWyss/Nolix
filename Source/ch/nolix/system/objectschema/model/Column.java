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
import ch.nolix.system.objectschema.midschemamodelmapper.ContentModelDtoMapper;
import ch.nolix.system.objectschema.modeltool.ColumnTool;
import ch.nolix.systemapi.midschema.adapter.ISchemaAdapter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modeleditor.IColumnEditor;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;

public final class Column extends AbstractSchemaObject implements IColumn {
  private static final String INITIAL_HEADER = StringCatalog.DEFAULT_STRING;

  private static final IContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  private static final IColumnEditor<Column> COLUMN_EDITOR = new ColumnEditor();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private final String id;

  private Table parentTable;

  private String name = INITIAL_HEADER;

  private FieldType fieldType = FieldType.VALUE_FIELD;

  private DataType dataType = DataType.STRING;

  private final IArrayList<? extends ITable> referenceableTables = ArrayList.createEmpty();

  private final IArrayList<? extends IColumn> backReferenceableColumns = ArrayList.createEmpty();

  private IContentModel contentModel = ValueModel.forDataType(DataType.INTEGER_4BYTE);

  public Column(
    final String name,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns,
    final IContentModel contentModel) {
    this(
      IdCreator.createIdOf10HexadecimalCharacters(),
      name,
      fieldType,
      dataType,
      referenceableTables,
      backReferenceableColumns,
      contentModel);
  }

  private Column(
    final String id,
    final String name,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns,
    final IContentModel contentModel) {
    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id = id;
    setName(name);
    this.referenceableTables.addAtEnd(referenceableTables);

    setContentModel(fieldType, dataType, referenceableTables, backReferenceableColumns, contentModel);
  }

  public static Column withIdAndNameAndContentModel(
    final String id,
    final String name,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns,
    final IContentModel contentModel) {
    return new Column(id, name, fieldType, dataType, referenceableTables, backReferenceableColumns, contentModel);
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
  public IContentModel getContentModel() {
    return contentModel;
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
  public Column setContentModel(
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns,
    final IContentModel contentModel) {
    COLUMN_EDITOR.setContentModelToColumn(this, fieldType, dataType, referenceableTables, backReferenceableColumns,
      contentModel);

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

  void setContentModelAttribute(final IContentModel contentModel) {
    Validator.assertThat(contentModel).thatIsNamed(IContentModel.class).isNotNull();

    this.contentModel = contentModel;
  }

  void internalSetContentModelToDatabase() {
    final var table = getStoredParentTable();
    final var tableName = table.getName();
    final var contentModelDto = CONTENT_MODEL_DTO_MAPPER.mapContentModelToContentModelDto(getContentModel());

    getStoredMidSchemaAdapter().setContentModel(tableName, getName(), contentModelDto);
  }

  void setNameAttribute(final String header) {
    this.name = header;
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
      return getStoredParentTable().getStoredColumns()
        .getStoredSelected(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this));
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
