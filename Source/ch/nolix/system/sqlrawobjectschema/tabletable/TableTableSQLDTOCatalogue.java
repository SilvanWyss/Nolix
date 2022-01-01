//package declaration
package ch.nolix.system.sqlrawobjectschema.tabletable;

//own imports
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;

//class
public final class TableTableSQLDTOCatalogue {
	
	//constant
	private static final ColumnDTO NAME_COLUMN_SQL_DTO =
	new ColumnDTO(TableTableColumn.NAME.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	public static final TableDTO TABLE_TABLE_SQL_DTO =
	new TableDTO(SystemDataTable.TABLE.getNameWithPrefix(),	NAME_COLUMN_SQL_DTO);
	
	//constructor
	private TableTableSQLDTOCatalogue() {}
}
