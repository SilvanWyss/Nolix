//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

//own imports
import ch.nolix.system.sqlintermediateschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlintermediateschema.structure.TableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//method
	public ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO createSQLColumnDTOFrom(final IColumnDTO column) {
		return new ColumnDTO(column.getHeader(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	}
	
	//method
	public ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO createSQLTableDTOFrom(final ITableDTO table) {
		return 
		new TableDTO(
			TableType.CONTENT_DATA.getPrefix() + table.getName(),
			table.getColumns().to(this::createSQLColumnDTOFrom)
		);
	}
}
