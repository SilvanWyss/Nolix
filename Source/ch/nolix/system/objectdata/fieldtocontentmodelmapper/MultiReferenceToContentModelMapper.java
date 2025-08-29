package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectschema.model.MultiReferenceModel;
import ch.nolix.systemapi.objectdata.model.IMultiReference;
import ch.nolix.systemapi.objectdata.modelmapper.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class MultiReferenceToContentModelMapper implements IFieldToContentModelMapper<IMultiReference<?>> {

  @Override
  public IContentModel mapFieldToContentModel(
    final IMultiReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var referenceableTableNames = field.getReferenceableTableNames();
    final var referenceableTables = referenceableTableNames.to(n -> referencedTables.getStoredFirst(t -> t.hasName(n)));

    return MultiReferenceModel.forReferenceableTables(referenceableTables);
  }
}
