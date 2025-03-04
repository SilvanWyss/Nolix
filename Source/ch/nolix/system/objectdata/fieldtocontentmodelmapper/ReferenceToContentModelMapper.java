package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.ReferenceModel;
import ch.nolix.systemapi.objectdataapi.fieldapi.IReference;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ReferenceToContentModelMapper implements IFieldToContentModelMapper<IReference<?>> {

  @Override
  public IContentModel mapFieldToContentModel(
    final IReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var referencedTableName = field.getReferencedTableName();
    final var referencedTable = referencedTables.getStoredFirst(t -> t.hasName(referencedTableName));

    return ReferenceModel.forReferencedTable(referencedTable);
  }
}
