//package declaration
package ch.nolix.system.sqlintermediateschema.structure;

//own imports
import ch.nolix.system.sqlintermediateschema.columnsystemtable.ColumnSystemTableColumn;
import ch.nolix.system.sqlintermediateschema.databasepropertysystemtable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlintermediateschema.tablesystemtable.TableSystemTableColumn;
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
	private static final ColumnDTO TABLE_NAME_COLUMN_SQL_DTO =
	new ColumnDTO(TableSystemTableColumn.NAME.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	public static final TableDTO DATABASE_PROPERTY_SYSTEM_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.DATABASE_PROPERTY.getNameWithPrefix(),
		DATABASE_PROPERTY_KEY_COLUMN_SQL_DTO,
		DATABASE_PROPERTY_VALUE_COLUMN_SQL_DTO
	);
	
	//constant
	private static final ColumnDTO COLUMN_TABLE_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.TABLE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO COLUMN_HEADER_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.HEADER.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO COLUMN_PROPERTY_TYPE_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.PROPERTY_TYPE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO COLUMN_DATA_TYPE_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.DATA_TYPE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO COLUMN_REFERENCED_TABLE_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.REFERENCED_TABLE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO COLUMN_BACK_REFERENCED_TABLE_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.BACK_REFERENCED_TABLE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO COLUMN_BACK_REFERENCED_COLUMN_SQL_DTO =
	new ColumnDTO(ColumnSystemTableColumn.BACK_REFERENCED_COLUM.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	public static final TableDTO TABLE_SYSTEM_TABLE_SQL_DTO =
	new TableDTO(SystemDataTable.TABLE.getNameWithPrefix(),	TABLE_NAME_COLUMN_SQL_DTO);
	
	//constant
	public static final TableDTO COLUMNS_SYSTEM_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.COLUMN.getNameWithPrefix(),
		COLUMN_TABLE_SQL_DTO,
		COLUMN_HEADER_SQL_DTO,
		COLUMN_PROPERTY_TYPE_SQL_DTO,
		COLUMN_DATA_TYPE_SQL_DTO,
		COLUMN_REFERENCED_TABLE_SQL_DTO,
		COLUMN_BACK_REFERENCED_TABLE_SQL_DTO,
		COLUMN_BACK_REFERENCED_COLUMN_SQL_DTO
	);
	
	//constructor
	private SystemDataTableSQLDTOCatalogue() {}
}
