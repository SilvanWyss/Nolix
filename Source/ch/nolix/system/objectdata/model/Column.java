package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidatorUnit;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.systemapi.middataapi.adapterapi.IDataReader;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;

public final class Column extends ImmutableDatabaseObject implements IColumnView<ITable<IEntity>> {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidatorUnit();

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
    RESOURCE_VALIDATOR.assertIsOpen(parentTable);
    RESOURCE_VALIDATOR.assertIsOpen(midDataReader);

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
