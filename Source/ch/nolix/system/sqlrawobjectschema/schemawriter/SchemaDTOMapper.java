//package declaration
package ch.nolix.system.sqlrawobjectschema.schemawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawobjectschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawobjectschema.structure.TableType;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableTableColumn;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//static attribute
	private final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO mSQLIdColumnDTO =
	new ColumnDTO(TableTableColumn.ID.getLabel(), SQLDatatypeCatalogue.TEXT);
	
	//attribute
	private final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO mSQLSaveStampColumnDTO;
	
	//constructor
	public SchemaDTOMapper(final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO) {
		
		Validator.assertThat(pSQLSaveStampColumnDTO).thatIsNamed("SQL save stamp DTO").isNotNull();
		
		mSQLSaveStampColumnDTO = pSQLSaveStampColumnDTO;
	}
	
	//method
	public ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO createSQLColumnDTOFrom(final IColumnDTO column) {
		return new ColumnDTO(column.getName(), SQLDatatypeCatalogue.TEXT);
	}
	
	//method
	public ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO createSQLTableDTOFrom(final ITableDTO table) {
		return new TableDTO(TableType.BASE_CONTENT_DATA.getPrefix() + table.getName(), createSQLColumnDTOsFrom(table));
	}
	
	//method
	private IContainer<ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO> createSQLColumnDTOsFrom(
		final ITableDTO table
	) {
		
		final var columns = LinkedList.withElements(mSQLIdColumnDTO);
		
		for (final var c : table.getColumns()) {
			columns.addAtEnd(createSQLColumnDTOFrom(c));
		}
		
		columns.addAtEnd(mSQLSaveStampColumnDTO);
		
		return columns;
	}
}

