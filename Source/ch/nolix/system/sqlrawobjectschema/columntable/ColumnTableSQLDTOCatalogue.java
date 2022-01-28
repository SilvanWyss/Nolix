//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//own imports
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class ColumnTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO ID_SQL_DTO =
	new ColumnDTO(TableTableColumn.ID.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO PARENT_TABLE_ID_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.PARENT_TABLE_ID.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO NAME_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.NAME.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO PROPERTY_TYPE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.PROPERTY_TYPE.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO DATA_TYPE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.DATA_TYPE.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO REFERENCED_TABLE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.REFERENCED_TABLE_ID.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO BACK_REFERENCED_COLUMN_ID_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getLabel(), SQLDatatypeCatalogue.TEXT);
	
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
