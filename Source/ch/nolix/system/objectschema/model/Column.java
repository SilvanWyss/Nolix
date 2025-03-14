package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programstructure.data.IdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectschema.rawschemadtomapper.ContentModelDtoMapper;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modeleditorapi.IColumnEditor;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaAdapter;

public final class Column extends AbstractSchemaObject implements IColumn {

  private static final String INITIAL_HEADER = StringCatalog.DEFAULT_STRING;

  private static final IContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  private static final IColumnEditor<Column> COLUMN_EDITOR = new ColumnEditor();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private final String id;

  private String name = INITIAL_HEADER;

  private IContainer<IContentModel> contentModels = //
  ImmutableList.withElement(ValueModel.forDataType(DataType.INTEGER_4BYTE));

  private Table parentTable;

  public Column(
    final String name,
    final IContainer<IContentModel> contentModels) {
    this(IdCreator.createIdOf10HexadecimalCharacters(), name, contentModels);
  }

  private Column(
    final String id,
    final String name,
    final IContainer<IContentModel> contentModels) {

    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id = id;
    setName(name);

    setContentModels(contentModels);
  }

  public static Column withIdAndNameAndContentModels(
    final String id,
    final String name,
    final IContainer<IContentModel> contentModels) {
    return new Column(id, name, contentModels);
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
  public String getName() {
    return name;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public IContainer<IContentModel> getContentModels() {
    return contentModels;
  }

  @Override
  public Table getStoredParentTable() {

    COLUMN_TOOL.assertBelongsToTable(this);

    return parentTable;
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
    || internalGetStoredRawSchemaAdapter().columnIsEmpty(getStoredParentTable().getName(), getName());
  }

  @Override
  public Column setContentModels(final IContainer<IContentModel> contentModels) {

    COLUMN_EDITOR.setContentModelsToColumn(this, contentModels);

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

  ISchemaAdapter internalGetStoredRawSchemaAdapter() {
    return ((Database) COLUMN_TOOL.getParentDatabase(this)).internalGetStoredRawSchemaAdapter();
  }

  void setContentModelsAttribute(final IContainer<IContentModel> contentModels) {
    this.contentModels = contentModels;
  }

  void internalSetContentModelToDatabase() {

    final var contentModelDtos = getContentModels().to(CONTENT_MODEL_DTO_MAPPER::mapContentModelToContentModelDto);

    //TODO: Adjust
    final var contentModelDto = contentModelDtos.getStoredFirst();

    internalGetStoredRawSchemaAdapter().setColumnContentModel(getId(), contentModelDto);
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
        .toMultiple(t -> t.getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this)));
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
