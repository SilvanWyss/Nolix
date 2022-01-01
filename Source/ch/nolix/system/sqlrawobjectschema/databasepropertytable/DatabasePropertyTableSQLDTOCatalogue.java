//package declaration
package ch.nolix.system.sqlrawobjectschema.databasepropertytable;

//own imports
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;

//class
public final class DatabasePropertyTableSQLDTOCatalogue {
	
	//constant
	private static final ColumnDTO KEY_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.KEY.getLabel(),	SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO VALUE_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.VALUE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	public static final TableDTO DATABASE_PROPERTY_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix(),
		KEY_COLUMN_SQL_DTO,
		VALUE_COLUMN_SQL_DTO
	);
	
	//constructor
	private DatabasePropertyTableSQLDTOCatalogue() {}
}
