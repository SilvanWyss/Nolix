//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class Column extends ImmutableDatabaseObject implements IColumn {

  //attribute
  private final String name;

  //attribute
  private final String id;

  //attribute
  private final IParameterizedFieldType parameterizedFieldType;

  //attribute
  private final Table<IEntity> parentTable;

  //constructor
  private Column(
    final String name,
    final String id,
    final IParameterizedFieldType parameterizedFieldType,
    final Table<IEntity> parentTable) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();
    GlobalValidator.assertThat(parameterizedFieldType).thatIsNamed(IParameterizedFieldType.class).isNotNull();
    GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    this.name = name;
    this.id = id;
    this.parameterizedFieldType = parameterizedFieldType;
    this.parentTable = parentTable;
  }

  //static method
  static Column withNameAndIdAndParameterizedFieldTypeAndParentTable(
    final String name,
    final String id,
    final IParameterizedFieldType parameterizedFieldType,
    final Table<IEntity> parentTable) {
    return new Column(name, id, parameterizedFieldType, parentTable);
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public IParameterizedFieldType getParameterizedFieldType() {
    return parameterizedFieldType;
  }

  //method
  @Override
  public ITable<IEntity> getStoredParentTable() {
    return parentTable;
  }

  //method
  @Override
  public boolean technicalContainsGivenValueInPersistedData(final String value) {
    return getStoredDataAndSchemaAdapter().tableContainsEntityWithGivenValueAtGivenColumn(
      getStoredParentTable().getName(),
      getName(),
      value);
  }

  //method
  private IDataAndSchemaAdapter getStoredDataAndSchemaAdapter() {
    return parentTable.internalGetStoredDataAndSchemaAdapter();
  }
}
