//package declaration
package ch.nolix.system.sqlrawobjectschema.multireferenceentrytable;

//own imports
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class MultiReferenceEntryTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN.getLabel(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	private static final IColumnDTO RECORD_COLUMN_SQL_DTO =
	new ColumnDTO(MultiReferenceEntryTableColumn.RECORD.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO REFERENCED_RECORD_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiReferenceEntryTableColumn.REFERENCED_RECORD.getLabel(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	public static final ITableDTO MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO =
	new TableDTO(
		MultiContentTable.MULTI_REFERENCE_ENTRY.getNameWithPrefix(),
		MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO,
		RECORD_COLUMN_SQL_DTO,
		REFERENCED_RECORD_COLUMN_SQL_DTO
	);
	
	//constructor
	private MultiReferenceEntryTableSQLDTOCatalogue() {}
}
