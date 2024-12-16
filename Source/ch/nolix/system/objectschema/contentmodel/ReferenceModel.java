package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;

public final class ReferenceModel extends AbstractReferenceModel {

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

  @Override
  public IContentModelDto toDto() {

    final var referencedTableIds = getReferencedTables().to(ITable::getId);

    return new ReferenceModelDto(getDataType(), referencedTableIds);
  }
}
