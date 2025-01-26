package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataReader;

public final class Column extends ImmutableDatabaseObject implements IColumnView<ITable<IEntity>> {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private final String id;

  private final String name;

  private final IContentModelView<ITable<IEntity>> contentModelView;

  private final Table<IEntity> parentTable;

  private final IDataReader rawDataReader;

  private Column(
    final String id,
    final String name,
    final IContentModelView<ITable<IEntity>> contentModelView,
    final Table<IEntity> parentTable,
    final IDataReader rawDataReader) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    GlobalValidator.assertThat(contentModelView).thatIsNamed(IContentModelView.class).isNotNull();
    RESOURCE_VALIDATOR.assertIsOpen(parentTable);
    RESOURCE_VALIDATOR.assertIsOpen(rawDataReader);

    this.id = id;
    this.name = name;
    this.contentModelView = contentModelView;
    this.parentTable = parentTable;
    this.rawDataReader = rawDataReader;
  }

  static Column withIdAndNameAndContentModelViewAndParentTableAndRawDataReader(
    final String id,
    final String name,
    final IContentModelView<ITable<IEntity>> contentModelView,
    final Table<IEntity> parentTable,
    final IDataReader rawDataReader) {
    return new Column(name, id, contentModelView, parentTable, rawDataReader);
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
    rawDataReader.tableContainsEntityWithGivenValueAtGivenColumn(
      getStoredParentTable().getName(),
      getName(),
      value);
  }

  @Override
  public boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    rawDataReader.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      getStoredParentTable().getName(),
      getName(),
      value,
      entitiesToIgnoreIds);
  }
}
