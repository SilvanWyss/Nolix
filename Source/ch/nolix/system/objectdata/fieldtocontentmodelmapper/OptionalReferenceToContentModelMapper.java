package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.OptionalReferenceModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class OptionalReferenceToContentModelMapper implements IFieldToContentModelMapper<IOptionalReference<?>> {

  @Override
  public IContainer<IContentModel> mapFieldToContentModels(
    final IOptionalReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = field.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return ImmutableList.withElement(OptionalReferenceModel.forReferencedTable(referencedTable));
  }
}
