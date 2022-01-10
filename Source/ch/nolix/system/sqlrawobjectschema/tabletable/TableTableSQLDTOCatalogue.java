//package declaration
package ch.nolix.system.sqlrawobjectschema.tabletable;

//own imports
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO NAME_COLUMN_SQL_DTO =
	new ColumnDTO(TableTableColumn.NAME.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	public static final ITableDTO TABLE_TABLE_SQL_DTO =
	new TableDTO(SystemDataTable.TABLE.getNameWithPrefix(),	NAME_COLUMN_SQL_DTO);
	
	//constructor
	private TableTableSQLDTOCatalogue() {}
}
