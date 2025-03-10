package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectschema.rawschemadtomapper.ContentModelDtoMapper;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modeleditorapi.IColumnEditor;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

public final class Column extends AbstractSchemaObject implements IColumn {

  private static final String INITIAL_HEADER = StringCatalog.DEFAULT_STRING;

  private static final IContentModel INITIAL_FIELD_TYPE = ValueModel.forDataType(DataType.INTEGER_4BYTE);

  private static final IContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  private static final IColumnEditor<Column> COLUMN_EDITOR = new ColumnEditor();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  private static final ContentModelMapper CONTENT_MODEL_MAPPER = new ContentModelMapper();

  private final String id;

  private String name = INITIAL_HEADER;

  private IContentModel contentModel = INITIAL_FIELD_TYPE;

  private Table parentTable;

  public Column(
    final String name,
    final IContentModel contentModel) {
    this(GlobalIdCreator.createIdOf10HexadecimalCharacters(), name, contentModel);
  }

  private Column(
    final String id,
    final String name,
    final IContentModel contentModel) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    this.id = id;
    setName(name);
    setContentModel(contentModel);
  }

  public static Column fromDto(final ColumnDto columnDto, final IContainer<ITable> tables) {
    return new Column(
      columnDto.id(),
      columnDto.name(),
      CONTENT_MODEL_MAPPER.mapContentModelDtoToContentModel(
        columnDto.contentModel(),
        tables));
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
  public IContentModel getContentModel() {
    return contentModel;
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
  public Column setContentModel(
    final IContentModel contentModel) {

    COLUMN_EDITOR.setContentModelToColumn(this, contentModel);

    return this;
  }

  @Override
  public Column setName(final String name) {

    COLUMN_EDITOR.setNameToColumn(this, name);

    return this;
  }

  IContainer<IColumn> getStoredBackReferencingColumns() {

    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return LinkedList.createEmpty();
    }

    return getStoredBackReferencingColumnsWhenIsReferenceColumn();
  }

  ISchemaAdapter internalGetStoredRawSchemaAdapter() {
    return ((Database) COLUMN_TOOL.getParentDatabase(this)).internalGetStoredRawSchemaAdapter();
  }

  void interanlSetContentModelAttribute(final IContentModel contentModel) {
    this.contentModel = contentModel;
  }

  void internalSetContentModelToDatabase() {
  
    final var contentModelDto = CONTENT_MODEL_DTO_MAPPER.mapContentModelToContentModelDto(contentModel);
  
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

  private IContainer<IColumn> getStoredBackReferencingColumnsWhenIsReferenceColumn() {

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
