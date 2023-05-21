//package declaration
package ch.nolix.system.sqldatabaserawdata.databaseinspector;

import ch.nolix.system.sqldatabaserawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
final class ColumnDefinitionMapper {
	
	//method
	public IColumnInfo createColumnDefinitionFrom(final IColumnDTO column) {
		return
		new ColumnInfo(
			column.getId(),
			column.getName(),
			column.getParametrizedPropertyType().getPropertyType(),
			column.getParametrizedPropertyType().getDataType(),
			0
		);
	}
}