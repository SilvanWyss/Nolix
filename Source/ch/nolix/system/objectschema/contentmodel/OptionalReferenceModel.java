package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IOptionalReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;

public final class OptionalReferenceModel extends AbstractReferenceModel implements IOptionalReferenceModel {

  private OptionalReferenceModel(final IContainer<ITable> referencedTables) {
    super(referencedTables);
  }

  public static OptionalReferenceModel forReferencedTable(final ITable referencedTable, ITable... referencedTables) {

    final var allReferencedTables = ContainerView.forElementAndArray(referencedTable, referencedTables);

    return forReferencedTables(allReferencedTables);
  }

  public static OptionalReferenceModel forReferencedTables(final IContainer<ITable> referencedTables) {
    return new OptionalReferenceModel(referencedTables);
  }

  @Override
  public ContentType getContentType() {
    return ContentType.OPTIONAL_REFERENCE;
  }

  @Override
  public IContentModelDto toDto() {

    final var referencedTableIds = getReferencedTables().to(ITable::getId);

    return new OptionalReferenceModelDto(getDataType(), referencedTableIds);
  }
}
