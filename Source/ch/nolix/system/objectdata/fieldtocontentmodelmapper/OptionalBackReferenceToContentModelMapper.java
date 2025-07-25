package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectschema.model.OptionalBackReferenceModel;
import ch.nolix.systemapi.objectdata.model.IOptionalBackReference;
import ch.nolix.systemapi.objectdata.modelmapper.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class OptionalBackReferenceToContentModelMapper
implements IFieldToContentModelMapper<IOptionalBackReference<?>> {

  @Override
  public IContentModel mapFieldToContentModel(
    final IOptionalBackReference<?> field,
    final IContainer<ITable> referencedTables) {

    final var backReferencedTableName = field.getBackReferencedTableName();
    final var backReferencedTable = referencedTables.getStoredFirst(t -> t.hasName(backReferencedTableName));
    final var backReferencedColumnName = field.getBackReferencedFieldName();
    final var columns = backReferencedTable.getStoredColumns();
    final var backReferencedColumn = columns.getStoredFirst(c -> c.hasName(backReferencedColumnName));

    return OptionalBackReferenceModel.forBackReferencedColumn(backReferencedColumn);
  }
}
