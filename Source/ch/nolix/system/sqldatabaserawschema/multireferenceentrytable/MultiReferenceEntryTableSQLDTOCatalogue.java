//package declaration
package ch.nolix.system.sqldatabaserawschema.multireferenceentrytable;

import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDTO;
import ch.nolix.system.sqldatabaserawschema.structure.MultiContentTable;
import ch.nolix.system.sqldatabaserawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class MultiReferenceEntryTableSQLDTOCatalogue {
	
	//constant
	private static final IColumnDTO MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	private static final IColumnDTO ENTITY_COLUMN_SQL_DTO =
	new ColumnDTO(MultiReferenceEntryTableColumn.ENTITY_ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final IColumnDTO REFERENCED_ENTITY_COLUMN_SQL_DTO =
	new ColumnDTO(
		MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName(),
		SQLDatatypeCatalogue.TEXT
	);
	
	//constant
	public static final ITableDTO MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO =
	new TableDTO(
		MultiContentTable.MULTI_REFERENCE_ENTRY.getQualifiedName(),
		MULTI_REFERENCE_COLUMN_COLUMN_SQL_DTO,
		ENTITY_COLUMN_SQL_DTO,
		REFERENCED_ENTITY_COLUMN_SQL_DTO
	);
	
	//constructor
	private MultiReferenceEntryTableSQLDTOCatalogue() {}
}
