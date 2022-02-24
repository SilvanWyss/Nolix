//package declaration
package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
final class ColumnDefinitionMapper {
	
	//method
	public IColumnInfo createColumnDefinitionFrom(final IColumnDTO column) {
		return
		new ColumnInfo(
			column.getId(),
			column.getName(),
			DataType.valueOf(column.getParametrizedPropertyType().getDataTypeFullClassName())
		);
	}
}
