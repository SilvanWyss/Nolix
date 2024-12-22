package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.contentmodel.MultiBackReferenceModel;
import ch.nolix.systemapi.objectdataapi.contentmodelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiBackReference;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class MultiBackReferenceToContentModelMapper
implements IFieldToContentModelMapper<IMultiBackReference<?>> {

  @Override
  public IContentModel mapFieldToContentModel(
    final IMultiBackReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var backReferencedTableName = field.getBackReferencedTableName();
    final var backReferencedTable = referencedTables.getStoredFirst(t -> t.hasName(backReferencedTableName));
    final var backReferencedColumnName = field.getBackReferencedFieldName();
    final var columns = backReferencedTable.getStoredColumns();
    final var backReferencedColumn = columns.getStoredFirst(c -> c.hasName(backReferencedColumnName));

    return MultiBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
  }
}
