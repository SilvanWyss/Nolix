package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectschema.model.ReferenceModel;
import ch.nolix.systemapi.objectdata.model.IReference;
import ch.nolix.systemapi.objectdata.modelmapper.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ReferenceToContentModelMapper implements IFieldToContentModelMapper<IReference<?>> {
  @Override
  public IContentModel mapFieldToContentModel(
    final IReference<?> field,
    final IContainer<ITable> referencedTables) {
    final var referenceableTableNames = field.getReferenceableTableNames();
    final var referenceableTables = referenceableTableNames.to(n -> referencedTables.getStoredFirst(t -> t.hasName(n)));

    return ReferenceModel.forReferenceableTables(referenceableTables);
  }
}
