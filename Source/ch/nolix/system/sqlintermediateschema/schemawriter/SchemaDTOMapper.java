//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.sqlintermediateschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlintermediateschema.structure.TableType;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//attribute
	private final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO mSQLSaveStampColumnDTO;
	
	//constructor
	public SchemaDTOMapper(final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO) {
		
		Validator.assertThat(pSQLSaveStampColumnDTO).thatIsNamed("SQL save stamp DTO").isNotNull();
		
		mSQLSaveStampColumnDTO = pSQLSaveStampColumnDTO;
	}
	
	//method
	public ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO createSQLColumnDTOFrom(final IColumnDTO column) {
		return new ColumnDTO(column.getHeader(), SQLDatatypeCatalogue.TEXT_DATA_TYPE);
	}
	
	//method
	public ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO createSQLTableDTOFrom(final ITableDTO table) {
		return new TableDTO(TableType.CONTENT_DATA.getPrefix() + table.getName(), createSQLColumnDTOsFrom(table));
	}
	
	//method
	private IContainer<ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO> createSQLColumnDTOsFrom(
		final ITableDTO table
	) {
		
		final var columns = table.getColumns().to(this::createSQLColumnDTOFrom);
		columns.addAtEnd(mSQLSaveStampColumnDTO);
		
		return columns;
	}
}

