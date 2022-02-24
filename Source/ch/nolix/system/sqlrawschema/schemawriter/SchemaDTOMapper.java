//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.structure.SQLDatatypeCatalogue;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class SchemaDTOMapper {
	
	//static attribute
	private final ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO mSQLIdColumnDTO =
	new ColumnDTO(TableTableColumn.ID.getName(), SQLDatatypeCatalogue.TEXT);
	
	//attribute
	private final ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO mSQLSaveStampColumnDTO;
	
	//constructor
	public SchemaDTOMapper(final ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO) {
		
		Validator.assertThat(pSQLSaveStampColumnDTO).thatIsNamed("SQL save stamp DTO").isNotNull();
		
		mSQLSaveStampColumnDTO = pSQLSaveStampColumnDTO;
	}
	
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
		
		final var columns = LinkedList.withElements(mSQLIdColumnDTO);
		
		for (final var c : table.getColumns()) {
			columns.addAtEnd(createSQLColumnDTOFrom(c));
		}
		
		columns.addAtEnd(mSQLSaveStampColumnDTO);
		
		return columns;
	}
}

