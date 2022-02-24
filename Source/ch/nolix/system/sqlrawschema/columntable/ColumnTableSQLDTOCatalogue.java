//package declaration
package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class ColumnTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO ID_SQL_DTO =
	new ColumnDTO(TableTableColumn.ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO PARENT_TABLE_ID_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.PARENT_TABLE_ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO NAME_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.NAME.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO PROPERTY_TYPE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.PROPERTY_TYPE.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO DATA_TYPE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.DATA_TYPE.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO REFERENCED_TABLE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.REFERENCED_TABLE_ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO BACK_REFERENCED_COLUMN_ID_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	public static final ITableDTO COLUMN_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.COLUMN.getFullName(),
		ID_SQL_DTO,
		PARENT_TABLE_ID_SQL_DTO,
		NAME_SQL_DTO,
		PROPERTY_TYPE_SQL_DTO,
		DATA_TYPE_SQL_DTO,
		REFERENCED_TABLE_SQL_DTO,
		BACK_REFERENCED_COLUMN_ID_SQL_DTO
	);
	
	//constructor
	private ColumnTableSQLDTOCatalogue() {}
}
