//package declaration
package ch.nolix.system.sqldatabaserawschema.multivalueentrytable;

import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.system.sqldatabaserawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.MultiContentTable;
import ch.nolix.system.sqldatabaserawschema.structure.SqlDatatypeCatalogue;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class MultiValueEntryTableSqlDtoCatalogue {
	
	//constant
	private static final IColumnDTO MULTI_VALUE_COLUMN_COLUMN_SQL_DTO =
	new ColumnDto(
		MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName(),
		SqlDatatypeCatalogue.TEXT
	);
	
	//constant
	private static final IColumnDTO ENTITY_COLUMN_SQL_DTO =
	new ColumnDto(MultiReferenceEntryTableColumn.ENTITY_ID.getName(), SqlDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO VALUE_COLUMN_SQL_DTO =
	new ColumnDto(
		MultiValueEntryTableColumn.VALUE.getName(),
		SqlDatatypeCatalogue.TEXT
	);
	
	//constant
	public static final ITableDTO MULTI_VALUE_ENTRY_TABLE_SQL_DTO =
	new TableDto(
		MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName(),
		MULTI_VALUE_COLUMN_COLUMN_SQL_DTO,
		ENTITY_COLUMN_SQL_DTO,
		VALUE_COLUMN_SQL_DTO
	);
	
	//constructor
	private MultiValueEntryTableSqlDtoCatalogue() {}
}
