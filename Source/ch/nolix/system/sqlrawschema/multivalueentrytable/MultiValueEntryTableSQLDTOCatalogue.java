//package declaration
package ch.nolix.system.sqlrawschema.multivalueentrytable;

//own imports
import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiContentTable;
import ch.nolix.system.sqlrawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class MultiValueEntryTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO MULTI_VALUE_COLUMN_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	private static final IColumnDTO ENTITY_COLUMN_SQL_DTO =
	new ColumnDTO(MultiReferenceEntryTableColumn.ENTITY_ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO VALUE_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiValueEntryTableColumn.VALUE.getName(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	public static final ITableDTO MULTI_VALUE_ENTRY_TABLE_SQL_DTO =
	new TableDTO(
		MultiContentTable.MULTI_VALUE_ENTRY.getFullName(),
		MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
		ENTITY_COLUMN_SQL_DTO,
		VALUE_COLUMN_SQL_DTO
	);
	
	//constructor
	private MultiValueEntryTableSQLDTOCatalogue() {}
}
