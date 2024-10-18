package ch.nolix.system.sqlrawschema.entityheadtable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.IndexTableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;

public final class EntityHeadTableDtoCatalogue {

  private static final ColumnDto TABLE_COLUMN_DTO = ColumnDto.withNameAndDataType(
    EntityHeadTableColumn.TABLE_ID.getName(), DatatypeTypeCatalogue.TEXT);

  private static final ColumnDto ENTITY_ID_COLUMN_DTO = ColumnDto.withNameAndDataType(
    EntityHeadTableColumn.ENTITY_ID.getName(), DatatypeTypeCatalogue.TEXT);

  public static final TableDto ENTITY_HEAD_TABLE_DTO = TableDto.withNameAndColumn(
    IndexTableType.ENTITY_HEAD.getQualifiedName(),
    TABLE_COLUMN_DTO,
    ENTITY_ID_COLUMN_DTO);

  private EntityHeadTableDtoCatalogue() {
  }
}
