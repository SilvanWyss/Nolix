//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.name.PascalCaseCatalogue;
import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//constant
	private static final ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO SQL_ID_COLUMN_DTO =
	new ColumnDTO(PascalCaseCatalogue.ID, SQLDatatypeCatalogue.TEXT);
	
	//constant
	private static final ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO SQL_SAVE_STAMP_COLUMN_DTO =
	new ColumnDTO(PascalCaseCatalogue.SAVE_STAMP, SQLDatatypeCatalogue.INTEGER);
	
	//method
	public ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO createSQLColumnDTOFrom(final IColumnDTO column) {
		return new ColumnDTO(column.getName(), SQLDatatypeCatalogue.TEXT);
	}
	
	//method
	public ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO createSQLTableDTOFrom(final ITableDTO table) {
		return new TableDTO(TableType.BASE_CONTENT_DATA.getNamePrefix() + table.getName(), createSQLColumnDTOsFrom(table));
	}
	
	//method
	private IContainer<ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO> createSQLColumnDTOsFrom(
		final ITableDTO table
	) {
		
		final var columns = new LinkedList<ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO>();
		
		columns.addAtEnd(SQL_ID_COLUMN_DTO);
		
		for (final var c : table.getColumns()) {
			columns.addAtEnd(createSQLColumnDTOFrom(c));
		}
		
		columns.addAtEnd(SQL_SAVE_STAMP_COLUMN_DTO);

		
		return columns;
	}
}

