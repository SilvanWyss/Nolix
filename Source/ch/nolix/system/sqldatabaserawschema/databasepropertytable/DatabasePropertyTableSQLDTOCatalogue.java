//package declaration
package ch.nolix.system.sqldatabaserawschema.databasepropertytable;

import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDTO;
import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;

//class
public final class DatabasePropertyTableSQLDTOCatalogue {
	
	//constant
	private static final ColumnDTO KEY_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.KEY.getLabel(),	SqlDatatypeCatalogue.TEXT);
	
	//constant
	private static final ColumnDTO VALUE_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.VALUE.getLabel(), SqlDatatypeCatalogue.TEXT);
	
	//constant
	public static final TableDTO DATABASE_PROPERTY_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.DATABASE_PROPERTY.getQualifiedName(),
		KEY_COLUMN_SQL_DTO,
		VALUE_COLUMN_SQL_DTO
	);
	
	//constructor
	private DatabasePropertyTableSQLDTOCatalogue() {}
}
