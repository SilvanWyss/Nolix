package ch.nolix.application.relationaldoc.backend.dataadapter;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.SmartObjectExaminer;
import ch.nolix.application.relationaldoc.backend.datamodel.SchemaCatalog;
import ch.nolix.application.relationaldoc.backend.datamodel.SmartObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.dataadapterapi.IDataAdapter;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;

public final class DataAdapter implements IDataAdapter {

  private static final SmartObjectExaminer CATEGORIZABLE_OBJECT_EXAMINER = new SmartObjectExaminer();

  private final ch.nolix.systemapi.objectdataapi.adapterapi.IDataAdapter internalDataAdapter;

  private DataAdapter(final ch.nolix.systemapi.objectdataapi.adapterapi.IDataAdapter internalDataAdapter) {

    Validator.assertThat(internalDataAdapter).thatIsNamed("internal data adapter").isNotNull();

    this.internalDataAdapter = internalDataAdapter;
  }

  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var nodeDataAdapter = //
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName(LowerCaseVariableCatalog.DATABASE)
      .andSchema(SchemaCatalog.RELATIONAL_DOC_SCHEMA);

    return new DataAdapter(nodeDataAdapter);
  }

  @Override
  public void close() {
    internalDataAdapter.close();
  }

  @Override
  public ISmartObject createObject() {

    final var object = new SmartObject();

    internalDataAdapter.insertEntity(object);

    return object;
  }

  @Override
  public IDataAdapter createEmptyCopy() {
    return new DataAdapter(internalDataAdapter.createEmptyCopy());
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredTopLevelObjects() {

    final var objectTable = internalDataAdapter.getStoredTableByEntityType(SmartObject.class);
    final var objects = objectTable.getStoredEntities();

    return objects.getStoredOthers(CATEGORIZABLE_OBJECT_EXAMINER::hasBaseTypes);
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
