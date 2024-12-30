package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IMultiReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class MultiReferenceModel extends AbstractReferenceModel implements IMultiReferenceModel {

  private MultiReferenceModel(final IContainer<ITable> referencedTables) {
    super(referencedTables);
  }

  public static MultiReferenceModel forReferencedTable(final ITable referencedTable, ITable... referencedTables) {

    final var allReferencedTables = ContainerView.forElementAndArray(referencedTable, referencedTables);

    return forReferencedTables(allReferencedTables);
  }

  public static MultiReferenceModel forReferencedTables(final IContainer<ITable> referencedTables) {
    return new MultiReferenceModel(referencedTables);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.MULTI_REFERENCE;
  }
}
