package ch.nolix.tech.relationaldoc.dataadapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableObjectEvaluator;
import ch.nolix.tech.relationaldoc.datamodel.AbstractableObject;
import ch.nolix.tech.relationaldoc.datamodel.SchemaCatalogue;
import ch.nolix.techapi.relationaldocapi.dataaapterapi.IDataAdapter;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public final class DataAdapter implements IDataAdapter {

  private static final AbstractableObjectEvaluator ABSTRACTABLE_OBJECT_EVALUATOR = new AbstractableObjectEvaluator();

  private final ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter internalDataAdapter;

  private DataAdapter(final ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter internalDataAdapter) {

    GlobalValidator
      .assertThat(internalDataAdapter)
      .thatIsNamed("internal data adapter")
      .isNotNull();

    this.internalDataAdapter = internalDataAdapter;
  }

  public static DataAdapter forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var nodeDataAdapter = NodeDataAdapter
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
  public void deleteObject(IAbstractableObject object) {
    ((AbstractableObject) object).delete();
  }

  @Override
  public IDataAdapter getEmptyCopy() {
    return new DataAdapter(internalDataAdapter.getEmptyCopy());
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredTopLevelObjects() {

    final var table = internalDataAdapter.getStoredTableByEntityType(AbstractableObject.class);

    final var objects = table.getStoredEntities();

    return objects.getStoredOthers(ABSTRACTABLE_OBJECT_EVALUATOR::hasBaseTypes);
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
