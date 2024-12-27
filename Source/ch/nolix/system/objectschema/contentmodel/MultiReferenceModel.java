package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IMultiReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;

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

  @Override
  public IContentModelDto toDto() {

    final var referencedTableIds = getReferencedTables().to(ITable::getId);

    return new MultiReferenceModelDto(getDataType(), referencedTableIds);
  }
}
