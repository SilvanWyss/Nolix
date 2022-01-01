//package declaration
package ch.nolix.system.sqlrawobjectschema.multivalueentrytable;

//own imports
import ch.nolix.system.sqlrawobjectschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class MultiValueEntryTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO MULTI_VALUE_COLUMN_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiValueEntryTableColumn.MULTI_VALUE_COLUMN.getLabel(),
		SQLDatatypeCatalogue.TEXT_DATA_TYPE
	);
	
	//constant
	private static final IColumnDTO RECORD_COLUMN_SQL_DTO =
	new ColumnDTO(MultiReferenceEntryTableColumn.RECORD.getLabel(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	
	//constant
	private static final IColumnDTO VALUE_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiValueEntryTableColumn.VALUE.getLabel(),
		SQLDatatypeCatalogue.TEXT_DATA_TYPE
	);
	
	//constant
	public static final ITableDTO MULTI_VALUE_ENTRY_TABLE_SQL_DTO =
	new TableDTO(
		MultiContentTable.MULTI_VALUE_ENTRY.getNameWithPrefix(),
		MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
		RECORD_COLUMN_SQL_DTO,
		VALUE_COLUMN_SQL_DTO
	);
	
	//constructor
	private MultiValueEntryTableSQLDTOCatalogue() {}
}
