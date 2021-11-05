//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.system.sqlintermediatedata.schema.ColumnDefinition;
import ch.nolix.system.sqlintermediatedata.sqlapi.IColumnDefinition;
import ch.nolix.techapi.databaseapi.datatypeapi.DataType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//class
public final class ColumnDefinitionMapper {
	
	//method
	public IColumnDefinition createColumnDefinitionFrom(final IColumnDTO column) {
		return
		new ColumnDefinition(
			column.getHeader(),
			DataType.valueOf(column.getParametrizedPropertyType().getDataTypeFullClassName())
		);
	}
}
