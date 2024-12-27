package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectschema.contentmodel.ValueModel;
import ch.nolix.system.objectschema.rawschemalinker.RawSchemaLinkerAdapter;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

public final class Column extends SchemaObject implements IColumn {

  private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;

  private static final IContentModel INITIAL_FIELD_TYPE = //
  ValueModel.forDataType(DataType.INTEGER_4BYTE);

  private static final ContentModelMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ContentModelMapper();

  private static final ColumnMutationValidator MUTATION_VALIDATOR = new ColumnMutationValidator();

  private static final ColumnMutationExecutor MUTATION_EXECUTOR = new ColumnMutationExecutor();

  private static final ColumnTool COLUMN_TOOL = new ColumnTool();

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

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id = id;
    setName(name);
    setContentModel(contentModel);
  }

  public static Column fromDto(final ColumnDto columnDto, final IContainer<ITable> tables) {
    return new Column(
      columnDto.id(),
      columnDto.name(),
      PARAMETERIZED_FIELD_TYPE_MAPPER.createContentModelFromDto(
        columnDto.contentModel(),
        tables));
  }

  @Override
  public boolean belongsToTable() {
    return (parentTable != null);
  }

  //metod
  @Override
  public void delete() {
    MUTATION_VALIDATOR.assertCanDeleteColumn(this);
    MUTATION_EXECUTOR.deleteColumn(this);
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
  public boolean isEmpty() {
    return //
    isNew()
    || internalGetRefRawSchemaAdapter().columnIsEmpty(this);
  }

  @Override
  public boolean isConnectedWithRealDatabase() {
    return (belongsToTable() && getStoredParentTable().isConnectedWithRealDatabase());
  }

  @Override
  public Column setName(final String name) {

    MUTATION_VALIDATOR.assertCanSetNameToColumn(this, name);
    MUTATION_EXECUTOR.setHeaderToColumn(this, name);

    return this;
  }

  @Override
  public Column setContentModel(
    final IContentModel contentModel) {

    MUTATION_VALIDATOR.assertCanSetParameterizedFieldTypeToColumn(this, contentModel);
    MUTATION_EXECUTOR.setParameterizedFieldTypeToColumn(this, contentModel);

    return this;
  }

  void assertIsEmpty() {
    if (containsAny()) {
      throw NonEmptyArgumentException.forArgument(this);
    }
  }

  void assertIsNotBackReferenced() {
    if (isBackReferenced()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is back referenced");
    }
  }

  IContainer<IColumn> getStoredBackReferencingColumns() {

    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return LinkedList.createEmpty();
    }

    return getStoredBackReferencingColumnsWhenIsReferenceColumn();
  }

  RawSchemaLinkerAdapter internalGetRefRawSchemaAdapter() {
    return ((Database) COLUMN_TOOL.getParentDatabase(this)).internalGetRefRawSchemaAdapter();
  }

  boolean isBackReferenced() {

    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return false;
    }

    return isBackReferencedWhenIsAnyReferenceColumn();
  }

  void setNameAttribute(final String header) {
    this.name = header;
  }

  void setParameterizedFieldTypeAttribute(
    final IContentModel contentModel) {
    this.contentModel = contentModel;
  }

  void setParameterizedFieldTypeToDatabase() {
    internalGetRefRawSchemaAdapter().setColumnContentModel(this, contentModel);
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
