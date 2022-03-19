//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

//own imports
import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;

//class
public final class DatabasePropertyTableSQLDTOCatalogue {
	
	//constant
	private static final ColumnDTO KEY_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.KEY.getLabel(),	SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final ColumnDTO VALUE_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.VALUE.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	public static final TableDTO DATABASE_PROPERTY_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.DATABASE_PROPERTY.getFullName(),
		KEY_COLUMN_SQL_DTO,
		VALUE_COLUMN_SQL_DTO
	);
	
	//constructor
	private DatabasePropertyTableSQLDTOCatalogue() {}
}
