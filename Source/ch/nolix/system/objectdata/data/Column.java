package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

public final class Column extends ImmutableDatabaseObject implements IColumn {

  private final String name;

  private final String id;

  private final IParameterizedFieldType parameterizedFieldType;

  private final Table<IEntity> parentTable;

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

  static Column withNameAndIdAndParameterizedFieldTypeAndParentTable(
    final String name,
    final String id,
    final IParameterizedFieldType parameterizedFieldType,
    final Table<IEntity> parentTable) {
    return new Column(name, id, parameterizedFieldType, parentTable);
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IParameterizedFieldType getParameterizedFieldType() {
    return parameterizedFieldType;
  }

  @Override
  public ITable<IEntity> getStoredParentTable() {
    return parentTable;
  }

  @Override
  public boolean internalContainsGivenValueInPersistedData(final String value) {
    return //
    getStoredDataAndSchemaAdapter().tableContainsEntityWithGivenValueAtGivenColumn(
      getStoredParentTable().getName(),
      getName(),
      value);
  }

  @Override
  public boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    getStoredDataAndSchemaAdapter().tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      getStoredParentTable().getName(),
      getName(),
      value,
      entitiesToIgnoreIds);
  }

  private IDataAndSchemaAdapter getStoredDataAndSchemaAdapter() {
    return parentTable.internalGetStoredDataAndSchemaAdapter();
  }
}
