package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.contentmodel.MultiReferenceModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class MultiReferenceToContentModelMapper implements IFieldToContentModelMapper<IMultiReference<?>> {

  @Override
  public IContentModel mapFieldToContentModel(
    final IMultiReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = field.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return MultiReferenceModel.forReferencedTable(referencedTable);
  }
}