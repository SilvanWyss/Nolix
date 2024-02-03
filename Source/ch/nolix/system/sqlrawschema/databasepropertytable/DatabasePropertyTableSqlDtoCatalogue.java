//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

import ch.nolix.system.sqlrawschema.datatype.DatatypeTypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;

//class
public final class DatabasePropertyTableSqlDtoCatalogue {

  //constant
  private static final ColumnDto KEY_COLUMN_SQL_DTO = new ColumnDto(DatabasePropertyTableColumn.KEY.getLabel(),
    DatatypeTypeCatalogue.TEXT);

  //constant
  private static final ColumnDto VALUE_COLUMN_SQL_DTO = new ColumnDto(
    DatabasePropertyTableColumn.VALUE.getLabel(), DatatypeTypeCatalogue.TEXT);

  //constant
  public static final TableDto DATABASE_PROPERTY_TABLE_SQL_DTO = TableDto.withNameAndColumn(
    MetaDataTableType.DATABASE_PROPERTY.getQualifiedName(),
    KEY_COLUMN_SQL_DTO,
    VALUE_COLUMN_SQL_DTO);

  //constructor
  private DatabasePropertyTableSqlDtoCatalogue() {
  }
}
