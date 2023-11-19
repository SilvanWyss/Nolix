//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedPropertyType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedValueType;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
public final class Column extends SchemaObject implements IColumn {

  //constant
  private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;

  //constant
  private static final ParameterizedPropertyType INITIAL_PROPERTY_TYPE = new ParameterizedValueType<>(
    DataType.INTEGER_4BYTE);

  //constant
  private static final ParameterizedPropertyTypeMapper PARAMETERIZED_PROPERTY_TYPE_MAPPER = //
  new ParameterizedPropertyTypeMapper();

  //constant
  private static final ColumnMutationValidator MUTATION_VALIDATOR = new ColumnMutationValidator();

  //constant
  private static final ColumnMutationExecutor MUTATION_EXECUTOR = new ColumnMutationExecutor();

  //constant
  private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();

  //attribute
  private final String id;

  //attribute
  private String name = INITIAL_HEADER;

  //attribute
  private IParameterizedPropertyType parameterizedPropertyType = INITIAL_PROPERTY_TYPE;

  //optional attribute
  private Table parentTable;

  //constructor
  public Column(
    final String name,
    final IParameterizedPropertyType parameterizedPropertyType) {
    this(GlobalIdCreator.createIdOf10HexadecimalCharacters(), name, parameterizedPropertyType);
  }

  //constructor
  private Column(
    final String id,
    final String name,
    final IParameterizedPropertyType parameterizedPropertyType) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();

    this.id = id;
    setName(name);
    setParameterizedPropertyType(parameterizedPropertyType);
  }

  //static method
  public static Column fromDto(final IColumnDto columnDto, final IContainer<ITable> tables) {
    return new Column(
      columnDto.getId(),
      columnDto.getName(),
      PARAMETERIZED_PROPERTY_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
        columnDto.getParameterizedPropertyType(),
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
  public IParameterizedPropertyType getParameterizedPropertyType() {
    return parameterizedPropertyType;
  }

  //method
  @Override
  public Table getParentTable() {

    COLUMN_HELPER.assertBelongsToTable(this);

    return parentTable;
  }

  //method
  @Override
  public boolean isEmpty() {

    if (COLUMN_HELPER.isNew(this)) {
      return true;
    }

    return internalGetRefRawSchemaAdapter().columnIsEmpty(this);
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
  public Column setParameterizedPropertyType(
    final IParameterizedPropertyType parameterizedPropertyType) {

    MUTATION_VALIDATOR.assertCanSetParameterizedPropertyTypeToColumn(this, parameterizedPropertyType);
    MUTATION_EXECUTOR.setParameterizedPropertyTypeToColumn(this, parameterizedPropertyType);

    return this;
  }

  //method
  @Override
  public ColumnDto toDto() {
    return new ColumnDto(getId(), getName(), getParameterizedPropertyType().toDto());
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

    if (!COLUMN_HELPER.isAReferenceColumn(this)) {
      return new LinkedList<>();
    }

    return getStoredBackReferencingColumnsWhenIsReferenceColumn();
  }

  //method
  RawSchemaAdapter internalGetRefRawSchemaAdapter() {
    return ((Database) COLUMN_HELPER.getParentDatabase(this)).internalGetRefRawSchemaAdapter();
  }

  //method
  boolean isBackReferenced() {

    if (!COLUMN_HELPER.isAReferenceColumn(this)) {
      return false;
    }

    return isBackReferencedWhenIsAnyReferenceColumn();
  }

  //method
  void setNameAttribute(final String header) {
    this.name = header;
  }

  //method
  void setParameterizedPropertyTypeAttribute(
    final IParameterizedPropertyType parameterizedPropertyType) {
    this.parameterizedPropertyType = parameterizedPropertyType;
  }

  //method
  void setParameterizedPropertyTypeToDatabase() {
    internalGetRefRawSchemaAdapter().setColumnParameterizedPropertyType(this, parameterizedPropertyType);
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

    if (COLUMN_HELPER.belongsToDatabase(this)) {
      return COLUMN_HELPER
        .getParentDatabase(this)
        .getStoredTables()
        .toFromGroups(
          t -> t.getStoredColumns().getStoredSelected(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this)));
    }

    if (belongsToTable()) {
      return getParentTable().getStoredColumns()
        .getStoredSelected(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this));
    }

    return new LinkedList<>();
  }

  //method
  private boolean isBackReferencedWhenIsAnyReferenceColumn() {

    if (COLUMN_HELPER.belongsToDatabase(this)) {
      return COLUMN_HELPER
        .getParentDatabase(this)
        .getStoredTables()
        .containsAny(t -> t.getStoredColumns().containsAny(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this)));
    }

    if (belongsToTable()) {
      return getParentTable().getStoredColumns().containsAny(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this));
    }

    return false;
  }
}
