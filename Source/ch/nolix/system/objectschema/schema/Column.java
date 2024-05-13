//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedFieldType;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedValueType;
import ch.nolix.system.objectschema.rawschemalinker.RawSchemaLinkerAdapter;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
public final class Column extends SchemaObject implements IColumn {

  //constant
  private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;

  //constant
  private static final ParameterizedFieldType INITIAL_FIELD_TYPE = //
  ParameterizedValueType.forDataType(DataType.INTEGER_4BYTE);

  //constant
  private static final ParameterizedFieldTypeMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ParameterizedFieldTypeMapper();

  //constant
  private static final ColumnMutationValidator MUTATION_VALIDATOR = new ColumnMutationValidator();

  //constant
  private static final ColumnMutationExecutor MUTATION_EXECUTOR = new ColumnMutationExecutor();

  //constant
  private static final ColumnTool COLUMN_TOOL = new ColumnTool();

  //attribute
  private final String id;

  //attribute
  private String name = INITIAL_HEADER;

  //attribute
  private IParameterizedFieldType parameterizedFieldType = INITIAL_FIELD_TYPE;

  //optional attribute
  private Table parentTable;

  //constructor
  public Column(
    final String name,
    final IParameterizedFieldType parameterizedFieldType) {
    this(GlobalIdCreator.createIdOf10HexadecimalCharacters(), name, parameterizedFieldType);
  }

  //constructor
  private Column(
    final String id,
    final String name,
    final IParameterizedFieldType parameterizedFieldType) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id = id;
    setName(name);
    setParameterizedFieldType(parameterizedFieldType);
  }

  //static method
  public static Column fromDto(final IColumnDto columnDto, final IContainer<ITable> tables) {
    return new Column(
      columnDto.getId(),
      columnDto.getName(),
      PARAMETERIZED_FIELD_TYPE_MAPPER.createParameterizedFieldTypeFromDto(
        columnDto.getParameterizedFieldType(),
        tables));
  }

  //method
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

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public IParameterizedFieldType getParameterizedFieldType() {
    return parameterizedFieldType;
  }

  //method
  @Override
  public Table getParentTable() {

    COLUMN_TOOL.assertBelongsToTable(this);

    return parentTable;
  }

  //method
  @Override
  public boolean isEmpty() {
    return //
    isNew()
    || internalGetRefRawSchemaAdapter().columnIsEmpty(this);
  }

  //method
  @Override
  public boolean isLinkedWithRealDatabase() {
    return (belongsToTable() && getParentTable().isLinkedWithRealDatabase());
  }

  //method
  @Override
  public Column setName(final String name) {

    MUTATION_VALIDATOR.assertCanSetNameToColumn(this, name);
    MUTATION_EXECUTOR.setHeaderToColumn(this, name);

    return this;
  }

  //method
  @Override
  public Column setParameterizedFieldType(
    final IParameterizedFieldType parameterizedFieldType) {

    MUTATION_VALIDATOR.assertCanSetParameterizedFieldTypeToColumn(this, parameterizedFieldType);
    MUTATION_EXECUTOR.setParameterizedFieldTypeToColumn(this, parameterizedFieldType);

    return this;
  }

  //method
  @Override
  public ColumnDto toDto() {
    return new ColumnDto(getId(), getName(), getParameterizedFieldType().toDto());
  }

  //method
  void assertIsEmpty() {
    if (containsAny()) {
      throw NonEmptyArgumentException.forArgument(this);
    }
  }

  //method
  void assertIsNotBackReferenced() {
    if (isBackReferenced()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is back referenced");
    }
  }

  //method
  IContainer<IColumn> getStoredBackReferencingColumns() {

    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return new LinkedList<>();
    }

    return getStoredBackReferencingColumnsWhenIsReferenceColumn();
  }

  //method
  RawSchemaLinkerAdapter internalGetRefRawSchemaAdapter() {
    return ((Database) COLUMN_TOOL.getParentDatabase(this)).internalGetRefRawSchemaAdapter();
  }

  //method
  boolean isBackReferenced() {

    if (!COLUMN_TOOL.isAReferenceColumn(this)) {
      return false;
    }

    return isBackReferencedWhenIsAnyReferenceColumn();
  }

  //method
  void setNameAttribute(final String header) {
    this.name = header;
  }

  //method
  void setParameterizedFieldTypeAttribute(
    final IParameterizedFieldType parameterizedFieldType) {
    this.parameterizedFieldType = parameterizedFieldType;
  }

  //method
  void setParameterizedFieldTypeToDatabase() {
    internalGetRefRawSchemaAdapter().setColumnParameterizedFieldType(this, parameterizedFieldType);
  }

  //method
  void setParentTableAttribute(final Table parentTable) {
    this.parentTable = parentTable;
  }

  //method
  @Override
  protected void noteClose() {
    //Does nothing.
  }

  //method
  private IContainer<IColumn> getStoredBackReferencingColumnsWhenIsReferenceColumn() {

    if (COLUMN_TOOL.belongsToDatabase(this)) {
      return COLUMN_TOOL
        .getParentDatabase(this)
        .getStoredTables()
        .toFromGroups(
          t -> t.getStoredColumns().getStoredSelected(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this)));
    }

    if (belongsToTable()) {
      return getParentTable().getStoredColumns()
        .getStoredSelected(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this));
    }

    return new LinkedList<>();
  }

  //method
  private boolean isBackReferencedWhenIsAnyReferenceColumn() {

    if (COLUMN_TOOL.belongsToDatabase(this)) {
      return COLUMN_TOOL
        .getParentDatabase(this)
        .getStoredTables()
        .containsAny(t -> t.getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this)));
    }

    if (belongsToTable()) {
      return getParentTable().getStoredColumns().containsAny(c -> COLUMN_TOOL.referencesBackGivenColumn(c, this));
    }

    return false;
  }
}
