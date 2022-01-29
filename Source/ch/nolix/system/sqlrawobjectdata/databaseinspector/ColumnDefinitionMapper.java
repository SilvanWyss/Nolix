//package declaration
package ch.nolix.system.sqlrawobjectdata.databaseinspector;

import ch.nolix.system.sqlrawobjectdata.schemainfo.ColumnDefinition;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//class
final class ColumnDefinitionMapper {
	
	//method
	public IColumnInfo createColumnDefinitionFrom(final IColumnDTO column) {
		return
		new ColumnDefinition(
			column.getId(),
			column.getName(),
			DataType.valueOf(column.getParametrizedPropertyType().getDataTypeFullClassName())
		);
	}
}
