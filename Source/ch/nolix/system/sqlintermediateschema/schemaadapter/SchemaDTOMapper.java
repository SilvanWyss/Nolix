//package declaration
package ch.nolix.system.sqlintermediateschema.schemaadapter;

//own imports
import ch.nolix.system.sqlintermediateschema.structure.TableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.DataTypeDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//constant
	private static final DataTypeDTO TEXT_DATA_TYPE =  new DataTypeDTO("NVARCHAR", "MAX");
	
	//method
	public ColumnDTO createSQLColumnDTOFrom(final IColumnDTO column) {
		return new ColumnDTO(column.getHeader(), TEXT_DATA_TYPE);
	}
	
	//method
	public TableDTO createSQLTableDTOFrom(final ITableDTO table) {
		return 
		new TableDTO(
			TableType.CONTENT_DATA.getPrefix() + table.getName(),
			table.getColumns().to(this::createSQLColumnDTOFrom)
		);
	}
}
