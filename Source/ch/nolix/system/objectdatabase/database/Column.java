//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class Column extends ImmutableDatabaseObject implements IColumn {

  //attribute
  private final String name;

  //attribute
  private final String id;

  //attribute
  private final IParameterizedPropertyType parameterizedPropertyType;

  //attribute
  private final Table<IEntity> parentTable;

  //constructor
  private Column(
    final String name,
    final String id,
    final IParameterizedPropertyType parameterizedPropertyType,
    final Table<IEntity> parentTable) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();
    GlobalValidator.assertThat(parameterizedPropertyType).thatIsNamed(IParameterizedPropertyType.class).isNotNull();
    GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    this.name = name;
    this.id = id;
    this.parameterizedPropertyType = parameterizedPropertyType;
    this.parentTable = parentTable;
  }

  //static method
  static Column withNameAndIdAndParameterizedPropertyTypeAndParentTable(
    final String name,
    final String id,
    final IParameterizedPropertyType parameterizedPropertyType,
    final Table<IEntity> parentTable) {
    return new Column(name, id, parameterizedPropertyType, parentTable);
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
  public IParameterizedPropertyType getParameterizedPropertyType() {
    return parameterizedPropertyType;
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
    return parentTable.internalGetRefDataAndSchemaAdapter();
  }
}
