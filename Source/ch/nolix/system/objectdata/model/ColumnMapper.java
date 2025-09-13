package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class ColumnMapper {

  public static IColumn mapMidSchemaColumnDtoToColumn(
    final ColumnDto midSchemaColumnDto,
    final Table<IEntity> parentTable,
    final IContainer<? extends ITable<IEntity>> tables) {
    final var contentModel = midSchemaColumnDto.contentModel();
    final var id = midSchemaColumnDto.id();
    final var name = midSchemaColumnDto.name();
    final var fieldType = contentModel.fieldType();
    final var dataTypeClass = (Class<Object>) contentModel.dataType().getDataTypeClass();
    final var referenceableTableIds = contentModel.referenceableTableIds();
    final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsAny(t::hasId));
    final var columns = tables.toMultiples(ITable::getStoredColumns);
    final var backReferenceableColumnIds = contentModel.backReferenceableColumnIds();
    final var backReferenceableColumns = columns.getStoredSelected(c -> backReferenceableColumnIds.contains(c.getId()));

    return //
    Column.withParentTableAndIdAndNameAndFieldTypeAndDataTypeClassAndReferenceableTablesAndBackReferenceableColumns(
      parentTable,
      id,
      name,
      fieldType,
      dataTypeClass,
      referenceableTables,
      backReferenceableColumns);
  }
}
