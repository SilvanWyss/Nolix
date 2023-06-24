//package declaration
package ch.nolix.system.sqldatabaserawschema.tabletable;

import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableTableSqlDtoCatalogue {
	
	//constant
	private static final IColumnDTO ID_COLUMN_SQL_DTO =
	new ColumnDto(
		TableTableColumn.ID.getName(),
		SqlDatatypeCatalogue.TEXT
	);
	
	//constant
	private static final IColumnDTO NAME_COLUMN_SQL_DTO =
	new ColumnDto(TableTableColumn.NAME.getName(), SqlDatatypeCatalogue.TEXT);
	
	//constant
	public static final ITableDTO TABLE_TABLE_SQL_DTO =
	new TableDto(SystemDataTable.TABLE.getQualifiedName(), ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);
	
	//constructor
	private TableTableSqlDtoCatalogue() {}
}
