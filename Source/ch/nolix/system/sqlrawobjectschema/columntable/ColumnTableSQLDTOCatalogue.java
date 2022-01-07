//package declaration
package ch.nolix.system.sqlrawobjectschema.columntable;

//own imports
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.SystemDataTable;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;

//class
public final class ColumnTableSQLDTOCatalogue {
	
	//constant
	private static final ColumnDTO PARENT_TABLE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.PARENT_TABLE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO NAME_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.NAME.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO PROPERTY_TYPE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.PROPERTY_TYPE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO DATA_TYPE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.DATA_TYPE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO REFERENCED_TABLE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.REFERENCED_TABLE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO BACK_REFERENCED_TABLE_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.BACK_REFERENCED_TABLE.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final ColumnDTO BACK_REFERENCED_COLUMN_SQL_DTO =
	new ColumnDTO(ColumnTableColumn.BACK_REFERENCED_COLUM.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	public static final TableDTO COLUMN_TABLE_SQL_DTO =
	new TableDTO(
		SystemDataTable.COLUMN.getNameWithPrefix(),
		PARENT_TABLE_SQL_DTO,
		NAME_SQL_DTO,
		PROPERTY_TYPE_SQL_DTO,
		DATA_TYPE_SQL_DTO,
		REFERENCED_TABLE_SQL_DTO,
		BACK_REFERENCED_TABLE_SQL_DTO,
		BACK_REFERENCED_COLUMN_SQL_DTO
	);
	
	//constructor
	private ColumnTableSQLDTOCatalogue() {}
}
