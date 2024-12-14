package ch.nolix.system.objectdata.parameterizedfieldtypemapper2;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.parameterizedfieldtypemapper2api.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ParameterizedBackReferenceTypeMapper
implements IParameterizedFieldTypeMapper<IBackReference<IEntity>> {

  @Override
  public IContentModel createParameterizedFieldTypeFromField(
    final IBackReference<IEntity> property,
    final IContainer<ITable> referencedTables) {

    final var backReferencedTableName = property.getBackReferencedTableName();
    final var backReferencedTable = referencedTables.getStoredFirst(t -> t.hasName(backReferencedTableName));
    final var backReferencedColumnName = property.getBackReferencedFieldName();
    final var columns = backReferencedTable.getStoredColumns();
    final var backReferencedColumn = columns.getStoredFirst(c -> c.hasName(backReferencedColumnName));

    return ParameterizedBackReferenceType.forBackReferencedColumn(backReferencedColumn);
  }
}
