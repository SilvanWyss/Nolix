package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataAdapterAndSchemaReader;

public final class Column extends ImmutableDatabaseObject implements IColumn {

  private final String name;

  private final String id;

  private final IContentModel contentModel;

  private final Table<IEntity> parentTable;

  private Column(
    final String name,
    final String id,
    final IContentModel contentModel,
    final Table<IEntity> parentTable) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    GlobalValidator.assertThat(contentModel).thatIsNamed(IContentModel.class).isNotNull();
    GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    this.name = name;
    this.id = id;
    this.contentModel = contentModel;
    this.parentTable = parentTable;
  }

  static Column withNameAndIdAndParameterizedFieldTypeAndParentTable(
    final String name,
    final String id,
    final IContentModel contentModel,
    final Table<IEntity> parentTable) {
    return new Column(name, id, contentModel, parentTable);
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
  public IContentModel getContentModel() {
    return contentModel;
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

  private IDataAdapterAndSchemaReader getStoredDataAndSchemaAdapter() {
    return parentTable.internalGetStoredDataAndSchemaAdapter();
  }
}
