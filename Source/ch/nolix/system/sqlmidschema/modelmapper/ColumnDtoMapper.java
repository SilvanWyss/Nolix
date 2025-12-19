package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschema.modelmapper.IColumnDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapJoinedColumnSqlRecordToColumnDto(final ISqlRecord joinedColumnSqlRecord) {
    final var id = joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX);
    final var name = joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX);

    final var fieldTypeString = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.FIELD_TYPE_INDEX);

    final var fieldType = FieldType.valueOf(fieldTypeString);

    final var dataTypeString = //
    joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.DATA_TYPE_INDEX);

    final var dataType = DataType.valueOf(dataTypeString);
    final var referenceableTableIdsString = joinedColumnSqlRecord.getStoredAtOneBasedIndex(6);
    ImmutableList<String> referenceableTableIds;

    if (referenceableTableIdsString != null) {
      referenceableTableIds = ImmutableList.fromArray(referenceableTableIdsString.split(","));
    } else {
      referenceableTableIds = ImmutableList.createEmpty();
    }

    final var backReferenceableColumnIdsString = joinedColumnSqlRecord.getStoredAtOneBasedIndex(7);
    ImmutableList<String> backReferenceableColumnIds;

    if (backReferenceableColumnIdsString != null) {
      backReferenceableColumnIds = ImmutableList.fromArray(backReferenceableColumnIdsString.split(","));
    } else {
      backReferenceableColumnIds = ImmutableList.createEmpty();
    }

    return new ColumnDto(id, name, fieldType, dataType, referenceableTableIds, backReferenceableColumnIds);
  }
}
