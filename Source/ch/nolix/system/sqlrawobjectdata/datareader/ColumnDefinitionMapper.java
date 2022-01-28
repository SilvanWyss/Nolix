//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

//own imports
import ch.nolix.system.sqlrawobjectdata.schema.ColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//class
public final class ColumnDefinitionMapper {
	
	//method
	public IColumnDefinition createColumnDefinitionFrom(final IColumnDTO column) {
		return
		new ColumnDefinition(
			column.getId(),
			column.getName(),
			DataType.valueOf(column.getParametrizedPropertyType().getDataTypeFullClassName())
		);
	}
}
