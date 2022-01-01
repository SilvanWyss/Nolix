//package declaration
package ch.nolix.system.sqlrawobjectschema.structure;

//own imports
import ch.nolix.system.sqlrawobjectschema.databasepropertysystemtable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;

//class
public final class SystemDataTableSQLDTOCatalogue {
	
	//constant
	private static final ColumnDTO DATABASE_PROPERTY_KEY_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.KEY.getLabel(),	SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO DATABASE_PROPERTY_VALUE_COLUMN_SQL_DTO =
	new ColumnDTO(DatabasePropertySystemTableColumn.VALUE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	public static final TableDTO DATABASE_PROPERTY_SYSTEM_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix(),
		DATABASE_PROPERTY_KEY_COLUMN_SQL_DTO,
		DATABASE_PROPERTY_VALUE_COLUMN_SQL_DTO
	);
}
