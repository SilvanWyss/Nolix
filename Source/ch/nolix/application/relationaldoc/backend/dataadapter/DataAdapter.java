package ch.nolix.application.relationaldoc.backend.dataadapter;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.AbstractableObjectExaminer;
import ch.nolix.application.relationaldoc.backend.datamodel.AbstractableObject;
import ch.nolix.application.relationaldoc.backend.datamodel.SchemaCatalogue;
import ch.nolix.applicationapi.relationaldocapi.backendapi.dataadapterapi.IDataAdapter;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;

public final class DataAdapter implements IDataAdapter {

  private static final AbstractableObjectExaminer ABSTRACTABLE_OBJECT_EXAMINER = new AbstractableObjectExaminer();

  private final ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter internalDataAdapter;

  private DataAdapter(final ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter internalDataAdapter) {

    GlobalValidator.assertThat(internalDataAdapter).thatIsNamed("internal data adapter").isNotNull();

    this.internalDataAdapter = internalDataAdapter;
  }

  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var nodeDataAdapter = //
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName(LowerCaseVariableCatalogue.DATABASE)
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    return new DataAdapter(nodeDataAdapter);
  }

  @Override
  public void close() {
    internalDataAdapter.close();
  }

  @Override
  public IAbstractableObject createObject() {

    final var object = new AbstractableObject();

    internalDataAdapter.insertEntity(object);

    return object;
  }

  @Override
  public IDataAdapter createEmptyCopy() {
    return new DataAdapter(internalDataAdapter.createEmptyCopy());
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredTopLevelObjects() {

    final var objectTable = internalDataAdapter.getStoredTableByEntityType(AbstractableObject.class);
    final var objects = objectTable.getStoredEntities();

    return objects.getStoredOthers(ABSTRACTABLE_OBJECT_EXAMINER::hasBaseTypes);
  }

  @Override
  public boolean hasChanges() {
    return internalDataAdapter.hasChanges();
  }

  @Override
  public boolean isClosed() {
    return internalDataAdapter.isClosed();
  }

  @Override
  public void saveChanges() {
    internalDataAdapter.saveChanges();
  }
}