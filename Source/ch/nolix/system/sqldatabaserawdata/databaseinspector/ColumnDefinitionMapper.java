//package declaration
package ch.nolix.system.sqldatabaserawdata.databaseinspector;

import ch.nolix.system.sqldatabaserawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
final class ColumnDefinitionMapper {

  //method
  public IColumnInfo createColumnDefinitionFrom(final IColumnDto column) {
    return new ColumnInfo(
        column.getId(),
        column.getName(),
        column.getParameterizedPropertyType().getPropertyType(),
        column.getParameterizedPropertyType().getDataType(),
        0);
  }
}
