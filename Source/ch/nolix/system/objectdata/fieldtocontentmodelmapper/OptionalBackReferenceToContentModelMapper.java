package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.OptionalBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class OptionalBackReferenceToContentModelMapper
implements IFieldToContentModelMapper<IOptionalBackReference<?>> {

  @Override
  public IContainer<IContentModel> mapFieldToContentModels(
    final IOptionalBackReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var backReferencedTableName = field.getBackReferencedTableName();
    final var backReferencedTable = referencedTables.getStoredFirst(t -> t.hasName(backReferencedTableName));
    final var backReferencedColumnName = field.getBackReferencedFieldName();
    final var columns = backReferencedTable.getStoredColumns();
    final var backReferencedColumn = columns.getStoredFirst(c -> c.hasName(backReferencedColumnName));

    return ImmutableList.withElement(OptionalBackReferenceModel.forBackReferencedColumn(backReferencedColumn));
  }
}
