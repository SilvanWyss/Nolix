package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ReferenceModel extends AbstractReferenceModel implements IReferenceModel {

  private ReferenceModel(final IContainer<ITable> referencedTables) {
    super(referencedTables);
  }

  public static ReferenceModel forReferencedTable(final ITable referencedTable, ITable... referencedTables) {

    final var allReferencedTables = ContainerView.forElementAndArray(referencedTable, referencedTables);

    return forReferencedTables(allReferencedTables);
  }

  public static ReferenceModel forReferencedTables(final IContainer<ITable> referencedTables) {
    return new ReferenceModel(referencedTables);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.REFERENCE;
  }
}
