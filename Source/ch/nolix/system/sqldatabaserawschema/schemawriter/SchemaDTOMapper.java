//package declaration
package ch.nolix.system.sqldatabaserawschema.schemawriter;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDTO;
import ch.nolix.system.sqldatabaserawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.TableType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//constant
	private static final ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO SQL_ID_COLUMN_DTO =
	new ColumnDTO(PascalCaseCatalogue.ID, SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO SQL_SAVE_STAMP_COLUMN_DTO =
	new ColumnDTO(PascalCaseCatalogue.SAVE_STAMP, SQLDatatypeCatalogue.INTEGER);
	
	//method
	public ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO createSQLColumnDTOFrom(final IColumnDTO column) {
		return new ColumnDTO(column.getName(), SQLDatatypeCatalogue.TEXT);
	}
	
	//method
	public ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO createSQLTableDTOFrom(final ITableDTO table) {
		return new TableDTO(TableType.ENTITY_TABLE.getNamePrefix() + table.getName(), createSQLColumnDTOsFrom(table));
	}
	
	//method
	private IContainer<ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO> createSQLColumnDTOsFrom(
		final ITableDTO table
	) {
		
		final var columns = new LinkedList<ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO>();
		
		columns.addAtEnd(SQL_ID_COLUMN_DTO);
		
		for (final var c : table.getColumns()) {
			columns.addAtEnd(createSQLColumnDTOFrom(c));
		}
		
		columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

		
		return columns;
	}
}

