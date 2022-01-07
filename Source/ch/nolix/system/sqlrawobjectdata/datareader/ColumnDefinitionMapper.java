//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

import ch.nolix.system.sqlrawobjectdata.schema.ColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//class
public final class ColumnDefinitionMapper {
	
	//method
	public IColumnDefinition createColumnDefinitionFrom(final IColumnDTO column) {
		return
		new ColumnDefinition(
			column.getName(),
			DataType.valueOf(column.getParametrizedPropertyType().getDataTypeFullClassName())
		);
	}
}
