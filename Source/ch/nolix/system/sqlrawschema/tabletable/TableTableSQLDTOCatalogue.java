//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO ID_COLUMN_SQL_DTO =
	new ColumnDTO(
		TableTableColumn.ID.getName(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	private static final IColumnDTO NAME_COLUMN_SQL_DTO =
	new ColumnDTO(TableTableColumn.NAME.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	public static final ITableDTO TABLE_TABLE_SQL_DTO =
	new TableDTO(SystemDataTable.TABLE.getFullName(), ID_COLUMN_SQL_DTO, NAME_COLUMN_SQL_DTO);
	
	//constructor
	private TableTableSQLDTOCatalogue() {}
}
