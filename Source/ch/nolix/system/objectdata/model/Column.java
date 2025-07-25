package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.middata.adapter.IDataReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;
import ch.nolix.systemapi.objectdata.schemaview.IContentModelView;

public final class Column extends AbstractImmutableDatabaseObject implements IColumnView<ITable<IEntity>> {

  private final String id;

  private final String name;

  private final IContentModelView<ITable<IEntity>> contentModelView;

  private final Table<IEntity> parentTable;

  private final IDataReader midDataReader;

  private Column(
    final String id,
    final String name,
    final IContentModelView<ITable<IEntity>> contentModelView,
    final Table<IEntity> parentTable,
    final IDataReader midDataReader) {

    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(contentModelView).thatIsNamed(IContentModelView.class).isNotNull();
    ResourceValidator.assertIsOpen(parentTable);
    ResourceValidator.assertIsOpen(midDataReader);

    this.id = id;
    this.name = name;
    this.contentModelView = contentModelView;
    this.parentTable = parentTable;
    this.midDataReader = midDataReader;
  }

  static Column withIdAndNameAndContentModelViewAndParentTableAndMidDataReader(
    final String id,
    final String name,
    final IContentModelView<ITable<IEntity>> contentModelView,
    final Table<IEntity> parentTable,
    final IDataReader midDataReader) {
    return new Column(name, id, contentModelView, parentTable, midDataReader);
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
  public IContentModelView<ITable<IEntity>> getContentModel() {
    return contentModelView;
  }

  @Override
  public ITable<IEntity> getStoredParentTable() {
    return parentTable;
  }

  @Override
  public boolean internalContainsGivenValueInPersistedData(final String value) {
    return //
    midDataReader.tableContainsEntityWithValueAtColumn(
      getStoredParentTable().getName(),
      getName(),
      value);
  }

  @Override
  public boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    midDataReader.tableContainsEntityWithValueAtColumnIgnoringEntities(
      getStoredParentTable().getName(),
      getName(),
      value,
      entitiesToIgnoreIds);
  }
}
