package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.MultiReferenceModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class MultiReferenceToContentModelMapper implements IFieldToContentModelMapper<IMultiReference<?>> {

  @Override
  public IContainer<IContentModel> mapFieldToContentModels(
    final IMultiReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = field.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return ImmutableList.withElement(MultiReferenceModel.forReferencedTable(referencedTable));
  }
}
